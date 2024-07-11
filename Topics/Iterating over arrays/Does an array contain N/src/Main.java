import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Read the array elements as a string

        String s = scanner.nextLine();
         // Consume the newline after the last number

        // Read the substring to search for
        String searchString = scanner.nextLine();

        // Check if the substring exists in the array
        System.out.println(s.contains(searchString));

        scanner.close();
    }
}