import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            String text = null;
            int num1 = 10;
            int num2 = 0;


            System.out.println(text.length());


            int result = num1 / num2;
            System.out.println("Result: " + result);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException divide by zero");
        }
    }
}