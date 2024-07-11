import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static void printMode(int[] numbers) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Count the frequency of each number
        for (int num : numbers) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Find the mode and its frequency
        int mode = numbers[0];
        int maxFrequency = 0;

        for (HashMap.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }

        // Print the mode and its frequency
        System.out.println(mode + " " + maxFrequency);



    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] map = new int [n];
        for (int i = 0; i < n; ++i) { 
            map[i] = scanner.nextInt();
        }

        printMode(map);
    }
}
