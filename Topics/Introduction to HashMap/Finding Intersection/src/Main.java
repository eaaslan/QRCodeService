import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    private static void printCommon(int[] firstArray, int[] secondArray) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        ArrayList<Integer> commonElements = new ArrayList<>();

        // Add elements of the first array to the map
        for (int num : firstArray) {
            map.put(num, true);
        }

        // Check for common elements in the second array
        for (int num : secondArray) {
            if (map.containsKey(num)) {
                commonElements.add(num);
                map.remove(num); // Remove to avoid duplicates in the result
            }
        }

        // Sort the common elements in ascending order
        Collections.sort(commonElements);

        // Print the common elements
        for (int num : commonElements) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the length of the arrays
        int n = scanner.nextInt();
        int[] firstArray = new int[n];
        int[] secondArray = new int[n];

        // Read the elements of the first array
        for (int i = 0; i < n; i++) {
            firstArray[i] = scanner.nextInt();
        }

        // Read the elements of the second array
        for (int i = 0; i < n; i++) {
            secondArray[i] = scanner.nextInt();
        }

        // Print the common elements
        printCommon(firstArray, secondArray);
    }
}
