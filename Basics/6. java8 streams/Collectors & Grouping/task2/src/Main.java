import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Partition a list of numbers into even and odd using partitioningBy.
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Map<Boolean,List<Integer>> evenNumbers =  numbers.stream().collect(Collectors.partitioningBy(num->num%2==0));
        System.out.println("even numbers ="+evenNumbers);

    }
}