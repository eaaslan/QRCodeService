import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Set <String> set = new TreeSet<>(Set.of("Gamma", "Alpha", "Omega")) ;
        set.add("Gamma");
        set.add("Alpha");
        set.add("Omega");
        System.out.println(set);
    }
}