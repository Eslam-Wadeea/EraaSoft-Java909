//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("try block");

            int result = 10 / 0;

        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception" + e.getMessage());

        } finally {
            System.out.println("Finally block");
        }
    }
}