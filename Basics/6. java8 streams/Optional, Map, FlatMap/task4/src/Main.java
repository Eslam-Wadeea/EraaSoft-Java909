import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Map a list of strings to their lengths.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<Integer> lengths = names.stream().filter(Objects::nonNull).map(String::length)
                .collect(Collectors.toList());

        System.out.println(names);
        System.out.println(lengths);
    }

    }
