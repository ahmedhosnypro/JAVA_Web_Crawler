import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if ((new StringBuilder(input).reverse().toString()).equals(input)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}