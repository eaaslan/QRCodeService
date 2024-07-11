import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[scan.nextInt()]; // [1, 2, 3, 4, 5]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        int x = scan.nextInt(); // 2

        int y = scan.nextInt(); // 3
        System.out.println( checkIf(arr, x, y));
       ; // false
    }

    private static boolean checkIf(int[] ints, int x, int y) {


        for (int i = 0; i < ints.length -1; i++) {
            if ( ints[i] == x && ints[i+1] == y) {
                return true;
            }
            if (ints[i] == y && ints[i+1] == x) {
               return true;
            }

        }
        return false;

    }
}