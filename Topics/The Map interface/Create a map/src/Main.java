import java.util.*;

class Main {
    private static Map<String, Integer> createStatuses() {
        Map<String, Integer> createStatuses = new HashMap<>();
        createStatuses.put("SUCCESS", 0);
        createStatuses.put("FAIL", 1);
        createStatuses.put("WARN", 2);
        
        
        return createStatuses;
    }

    // do not change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> map = createStatuses();
        String status = scanner.next();
        int result = map.getOrDefault(status, -1);
        System.out.println(result);
    }
}
