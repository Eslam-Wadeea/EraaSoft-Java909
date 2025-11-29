import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Extract all unique characters from a list of words.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        Set<Character> uniqueChars = names.stream()
                .filter(Objects::nonNull)
                .flatMap(name -> name.chars()
                        .mapToObj(c -> (char) c))
                .filter(c -> !Character.isWhitespace(c))
                .collect(Collectors.toSet());

        System.out.println(uniqueChars);
    }


    }
