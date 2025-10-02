
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentID;
    private String name;
    private double grade;

    public Student(String studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return "Student[ID=" + studentID + ", Name=" + name + ", Grade=" + grade + "]";
    }
}

import java.io.*;
import java.util.Scanner;

public class StudentSerializeDemo {
    private static final String FILE_NAME = "student.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Create & serialize a Student");
        System.out.println("2. Deserialize & display Student");
        System.out.print("Choose (1 or 2): ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter student ID: ");
                String id = sc.nextLine();
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter grade (number): ");
                double grade = sc.nextDouble();

                Student s = new Student(id, name, grade);
                serializeStudent(s);
                System.out.println("Student serialized to '" + FILE_NAME + "'");
                break;

            case 2:
                Student deserialized = deserializeStudent();
                if (deserialized != null) {
                    System.out.println("Deserialized Student: " + deserialized);
                } else {
                    System.out.println("No student found or failed to deserialize.");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }

    private static void serializeStudent(Student s) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(s);
        } catch (IOException e) {
            System.err.println("Serialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Student deserializeStudent() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("File '" + FILE_NAME + "' does not exist.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof Student) return (Student) obj;
            else {
                System.err.println("File did not contain a Student object.");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
