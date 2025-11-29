import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Find names starting with a specific letter from a list of strings.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String>res =  names.stream().filter(Objects::nonNull).filter(name -> !name.isEmpty()).filter(name->name.charAt(0)=='A').toList();
        System.out.println(res);

    }
}