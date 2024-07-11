# QRCodeService

QRCodeService is a RESTful API that generates QR codes with customizable error correction levels. The service allows users to specify various parameters, including size, error correction level, and image format, to generate QR codes according to their needs.

## Features

- Generate QR codes with different error correction levels (L, M, Q, H).
- Customize QR code size.
- Specify the output image format (default is PNG).
- Handle invalid parameters with appropriate error messages.
- Default values for optional parameters: size (250), correction (L), type (png).

## Error Correction Levels

QR codes have four error correction levels, which determine how much of the code can be damaged or obscured while still being readable:

- **L (Low):** Can withstand up to approximately 7% damage.
- **M (Medium):** Can withstand up to approximately 15% damage.
- **Q (Quartile):** Can withstand up to approximately 25% damage.
- **H (High):** Can withstand up to approximately 30% damage.

Higher error correction levels require more space, reducing the amount of data that can be stored in the QR code.

## API Endpoints

### GET /api/qrcode

Generates a QR code based on the provided parameters.

#### Request Parameters

- `contents` (required): The content to be encoded in the QR code.
- `size` (optional): The size of the QR code image (default: 250).
- `correction` (optional): The error correction level (default: L). Accepted values: L, M, Q, H.
- `type` (optional): The image format (default: png).

#### Sample Request

```http
GET /api/qrcode?contents=HelloWorld&size=300&correction=M&type=png
