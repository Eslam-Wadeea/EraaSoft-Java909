import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Calculate the average of a list of doubles.
        List<Double> numbers = Arrays.asList(10.0, 5.0, 3.0, 7.0, 2.0, 10.0, 5.0, 8.0, 9.0, 0.0, -3.0, 4.0);
        double average = numbers.stream().mapToDouble(Double::doubleValue).sum() / numbers.size();
        System.out.println("average=" +average);

    }
}