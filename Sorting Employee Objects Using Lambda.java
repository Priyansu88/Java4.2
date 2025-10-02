
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeSortDemo {
    static class Employee {
        String name;
        int age;
        double salary;

        Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', age=%d, salary=%.2f}", name, age, salary);
        }
    }

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Zara", 29, 70000));
        list.add(new Employee("Amit", 35, 50000));
        list.add(new Employee("Bhavya", 22, 45000));
        list.add(new Employee("Rohit", 40, 95000));
        list.add(new Employee("Maya", 29, 70000));

        System.out.println("Original list:");
        list.forEach(System.out::println);

        // Sort by name (alphabetically)
        list.sort(Comparator.comparing(e -> e.name));
        System.out.println("\nSorted by name (A → Z):");
        list.forEach(System.out::println);

        // Sort by age (ascending)
        list.sort(Comparator.comparingInt(e -> e.age));
        System.out.println("\nSorted by age (ascending):");
        list.forEach(System.out::println);

        // Sort by salary (descending)
        list.sort(Comparator.comparingDouble((Employee e) -> e.salary).reversed()
                  .thenComparing(e -> e.name)); // tie-breaker by name
        System.out.println("\nSorted by salary (descending), tie by name:");
        list.forEach(System.out::println);
    }
}