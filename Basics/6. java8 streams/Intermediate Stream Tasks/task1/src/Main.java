import java.util.Arrays;
import java.util.List;
import java.util.Objects;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Count the number of strings longer than 5 characters.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<String> generatedNames = names.stream().filter(Objects::nonNull).filter(name -> name.length()>5).toList();
        System.out.println(generatedNames.size());

    }
}