import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number");
        int firstNumber = scanner.nextInt();
        System.out.println("Enter second number");
        int secondNumber = scanner.nextInt();

        try {
            int x  = firstNumber / secondNumber;
            System.out.println(firstNumber + " / " + secondNumber + " is " + x);
        }catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception Division by zero");
        }

    }
}