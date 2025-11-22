//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            methodB();
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    // Method B calls method A
    public static void methodB() throws Exception {
        methodA();
    }

    // Method A throws an exception
    public static void methodA() throws Exception {
        throw new Exception("MethodA Exception");
    }
}