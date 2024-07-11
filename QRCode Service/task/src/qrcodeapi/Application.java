package qrcodeapi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Main application class for the QR Code API.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configures a converter for BufferedImage to support image responses.
     *
     * @return HttpMessageConverter for BufferedImage
     */
    @Bean
    public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}

/**
 * REST controller for handling QR code generation requests.
 * This class provides endpoints for generating QR codes and checking API health.
 */
@RestController
@RequestMapping("/api")
class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private static final Set<String> VALID_TYPES = Set.of("png", "jpeg", "gif");
    private static final Set<Character> VALID_CORRECTION = Set.of('L', 'M', 'Q', 'H');

    /**
     * Health check endpoint.
     *
     * @return ResponseEntity with status 200 OK if the service is running
     */
    @GetMapping("/health")
    ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }

    /**
     * Generates a QR code based on the provided parameters.
     *
     * @param size      The size of the QR code image (default: 250)
     * @param type      The image type (png, jpeg, or gif; default: png)
     * @param contents  The content to encode in the QR code
     * @param correction The error correction level (L, M, Q, H; default: L)
     * @return ResponseEntity containing the QR code image or an error message
     */
    @GetMapping(path = "/qrcode")
    public ResponseEntity<?> getImage(
            @RequestParam(defaultValue = "250") int size,
            @RequestParam(defaultValue = "png") String type,
            @RequestParam String contents,
            @RequestParam (defaultValue = "L") char correction

    )  {




        // Validate contents (highest priority)
        if (contents == null || contents.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Contents cannot be null or blank"));
        }

        // Validate size
        if (size < 150 || size > 350) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Image size must be between 150 and 350 pixels"));
        }
        if (correction != 'L' && correction != 'M' && correction != 'Q' && correction != 'H') {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Permitted error correction levels are L, M, Q, H"
                            ));
        }

        // Validate and normalize type
        String normalizedType = type.toLowerCase();
        if (!VALID_TYPES.contains(normalizedType)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Only png, jpeg and gif image types are supported"));
        }

        try {
            // Generate QR code image
            BufferedImage bufferedImage = createQRCode(contents, size, correction);
            byte[] imageBytes = convertToByteArray(bufferedImage, normalizedType);

            // Determine the appropriate media type
            MediaType mediaType = switch (normalizedType) {
                case "png" -> MediaType.IMAGE_PNG;
                case "jpeg" -> MediaType.IMAGE_JPEG;
                case "gif" -> MediaType.IMAGE_GIF;
                default -> throw new IllegalArgumentException("Unsupported image type");
            };

            // Return the image with the correct content type
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(imageBytes);
        } catch (WriterException | IOException e) {
            logger.error("Error generating QR code", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to generate QR code"));
        }
    }

    /**
     * Creates a QR code image with the given content, size, and error correction level.
     *
     * @param data      The content to encode in the QR code
     * @param size      The size of the QR code image
     * @param correction The error correction level (L, M, Q, H)
     * @return A BufferedImage containing the QR code
     * @throws WriterException If there's an error during QR code generation
     */
    private static BufferedImage createQRCode(String data, int size, char correction) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        ErrorCorrectionLevel errorCorrectionLevel = switch(correction) {
            case 'L' -> ErrorCorrectionLevel.L;
            case 'M' -> ErrorCorrectionLevel.M;
            case 'Q' -> ErrorCorrectionLevel.Q;
            case 'H' -> ErrorCorrectionLevel.H;
            default -> throw new IllegalArgumentException("Invalid error correction level");
        };
        Map<EncodeHintType, ?> hints = Map.of(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        BitMatrix bitMatrix;
        try {
            bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);

        } catch (WriterException e) {
            throw new WriterException(e);
        }
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * Converts a BufferedImage to a byte array in the specified format.
     *
     * @param image  The BufferedImage to convert
     * @param format The desired image format (png, jpeg, or gif)
     * @return A byte array containing the image data
     * @throws IOException If there's an error during image conversion
     */
    private byte[] convertToByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
}