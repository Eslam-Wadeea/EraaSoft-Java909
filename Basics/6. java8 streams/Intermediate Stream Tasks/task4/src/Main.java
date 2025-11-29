import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Collect elements into a Set instead of a List.\
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        Set<Integer> numberSet = numbers.stream().filter(num->num>0).collect(Collectors.toSet());
        System.out.println(numberSet);

    }
}