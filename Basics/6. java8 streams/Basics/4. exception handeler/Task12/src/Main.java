//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Outer try block");
            try {
                System.out.println("Inner try block");
                int x = 10 / 0;
            } catch (NullPointerException e) {
                System.out.println("NullPointerException");
            }
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception caught dividing by zero");
        }
    }
}