import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number in a String");
        String input = scanner.next();
        try {
            System.out.println(("number in int type "+ Integer.parseInt(input)));
        }
        catch (NumberFormatException e){
            System.out.println("Not a valid number");
        }

    }
}