import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Find the maximum and minimum value in a list.
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        int max = numbers.stream().max(Integer::compareTo).get();
        System.out.println("maximum = "+max);
        int min = numbers.stream().min(Integer::compareTo).get();
        System.out.println("Minimum= "+min);

    }
}