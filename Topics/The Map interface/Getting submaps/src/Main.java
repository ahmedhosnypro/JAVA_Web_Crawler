import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fromInclusive = scanner.nextInt();
        int toInclusive = scanner.nextInt() + 1;
        int numOfEntries = scanner.nextInt();
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        for (int i = 0; i < numOfEntries; i++) {
            int key = scanner.nextInt();
            String value = scanner.nextLine();
            sortedMap.put(key, value);
        }

        sortedMap.subMap(fromInclusive, toInclusive)
                .forEach((n, s) -> System.out.println(n + s));
    }
}