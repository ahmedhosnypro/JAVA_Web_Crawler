import java.util.Collections;
import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int num1 = Collections.frequency(list1, elem);
        int num2 = Collections.frequency(list2, elem);
        return num1 == num2;
    }
}
