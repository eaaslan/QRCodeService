import java.util.Collections;
import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method
        // there is no need to input data from the command line
        // instead, use arguments elem, list1 and list2


//        int count1 = 0;
//        int count2 = 0;
//
//
//        for (Integer integer : list1) {
//            if (integer == elem) {
//                count1++;
//            }
//        }
//        for (Integer integer : list2) {
//            if (integer == elem) {
//                count2++;
//            }
//        }

//        return count2 == count1;

        return Collections.frequency(list1, elem) == Collections.frequency(list2, elem);
    }
}