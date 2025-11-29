import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Check if any number is divisible by 5 in a list.
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        boolean x = numbers.stream().filter(num->num!=0).anyMatch(number -> number % 5 == 0);
        System.out.println(x);

    }
}