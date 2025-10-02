
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentStreamDemo {
    static class Student {
        String name;
        double marks; // percent or marks

        Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return String.format("Student{name='%s', marks=%.2f}", name, marks);
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Priyansu", 88.5));
        students.add(new Student("Arjun", 72.0));
        students.add(new Student("Neha", 91.0));
        students.add(new Student("Sana", 76.5));
        students.add(new Student("Vikram", 65.0));
        students.add(new Student("Ria", 78.0));

        System.out.println("All students:");
        students.forEach(System.out::println);

        // Filter students with marks > 75, sort by marks (descending or ascending? we'll show descending)
        List<Student> filteredSorted = students.stream()
            .filter(s -> s.marks > 75.0)
            .sorted(Comparator.comparingDouble((Student s) -> s.marks).reversed())
            .collect(Collectors.toList());

        System.out.println("\nStudents with marks > 75, sorted by marks (high → low):");
        filteredSorted.forEach(System.out::println);

        // If you only want names:
        List<String> names = filteredSorted.stream()
            .map(s -> s.name)
            .collect(Collectors.toList());

        System.out.println("\nNames of filtered students:");
        names.forEach(System.out::println);
    }
}