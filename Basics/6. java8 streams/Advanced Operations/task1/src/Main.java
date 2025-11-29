import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Sort a List of employees by salary then by name.

        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5000),
                new Employee("Sara", 27, "IT", 7000),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8000)
        );
        List<Employee> sortedEmployees =employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName)).toList();
        System.out.println(sortedEmployees);

    }
}