import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int size = scanner.nextInt();

        // Create the array
        int[] numbers = new int[size];

        // Populate the array
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        // Create a boolean variable
        boolean isSorted = true;

        // Iterate over each index and check if it's bigger than the next
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                // Set the boolean to false if the condition becomes true
                isSorted = false;
                break;
            }
        }

        // Print the boolean
        System.out.println(isSorted);

        scanner.close();
    }
}