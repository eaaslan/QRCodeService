import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        ArrayList <Integer> list = new ArrayList<>();

        String arrList = scanner.nextLine();

        String[] arr = arrList.split(" ");
        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }

        int n = scanner.nextInt();
        int distance = Math.abs(list.get(0) - n);


        for (Integer integer : list) {
            if (Math.abs(integer - n) < distance) {
                distance = Math.abs(integer - n);
            }
        }
        Collections.sort(list);
        for (int num :list){
            if (Math.abs(num -n) == distance){
                System.out.print(num + " ");
            }
        }
    }
}