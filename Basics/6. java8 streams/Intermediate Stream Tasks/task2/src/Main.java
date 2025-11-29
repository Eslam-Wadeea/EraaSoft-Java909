import java.util.Arrays;
import java.util.List;
import java.util.Objects;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Find the first element in a stream that matches a given condition.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> generatedNames = names.stream().filter(Objects::nonNull).filter(name -> !name.isEmpty()).filter(name->name.charAt(0)=='S').toList();
        System.out.println(generatedNames.stream().findFirst());

    }
}