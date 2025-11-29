import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // filter even numbers from a list of integers
        List<Integer> numbers = Arrays.asList(10,5,3,7,2,10,5,8,9,0,-3,4);
        List<Integer>nums = numbers.stream().filter(number -> number % 2 == 0).toList();
        System.out.println(nums);

    }

}