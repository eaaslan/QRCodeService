import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int length = scanner.nextInt();

        // Read the array as a string
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < length; i++) {
            numbers.append(scanner.nextInt()).append(" ");
        }

        // Read n and m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String input = n + " " + m;
        String reversedInput = m + " " + n;

        // Check if n and m are adjacent in either order
        boolean areAdjacent = numbers.toString().contains(input) || numbers.toString().contains(reversedInput);

        // Print the result (true if NOT adjacent, false if adjacent)
        System.out.println(!areAdjacent);

        scanner.close();
    }
}