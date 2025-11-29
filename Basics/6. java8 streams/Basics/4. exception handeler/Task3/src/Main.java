public class Main {

    public static void main(String[] args) {
        String str1 = "string";
        String str2 = null;

        System.out.println(convertToUpper(str1)); // Output: HELLO
        System.out.println(convertToUpper(str2)); // Output: Input string is null
    }

    public static String convertToUpper(String input) {
        try {
            return input.toUpperCase();
        } catch (NullPointerException e) {
            return "null pointer exception";
        }
    }
}
