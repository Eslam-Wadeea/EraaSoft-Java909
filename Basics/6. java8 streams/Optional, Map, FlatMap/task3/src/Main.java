import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Filter a list of Optionals and collect non-empty values.

        List<Optional<String>> list = Arrays.asList(
                Optional.of("Ali"),
                Optional.empty(),
                Optional.of("Sara"),
                Optional.empty(),
                Optional.of("Mona")
        );

        List<String> result = list.stream()
                .flatMap(Optional::stream)
                .toList();

        System.out.println(result);

    }
}