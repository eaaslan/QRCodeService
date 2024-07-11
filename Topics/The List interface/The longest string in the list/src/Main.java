import java.util.*;

import static java.util.Collections.fill;
import static java.util.Collections.max;
import static java.util.Comparator.comparing;


public class Main {

    static void changeList(List<String> list) {
        // write your code here

//       int  longestIndex =0;
//
//       for (int i = 0; i < list.size(); i++) {
//           if (list.get(i).length() > list.get(longestIndex).length()) {
//               longestIndex = i;
//           }
//       }
//
//       for (int i = 0; i < list.size(); i++) {
//           list.set(i, list.get(longestIndex));
//       }

        fill(list, max(list, comparing(String::length)));

    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}