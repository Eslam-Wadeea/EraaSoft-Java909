import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Partition students into pass/fail groups based on grade.

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        Map<Boolean,List<Student>> students2 = students.stream().collect(Collectors.partitioningBy(n->n.grade>75));
        System.out.println("Students who passed "+students2.get(true));
        System.out.println("students who failed "+students2.get(false));

    }
}