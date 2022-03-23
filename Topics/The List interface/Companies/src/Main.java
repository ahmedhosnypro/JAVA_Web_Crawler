import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
    }
}
