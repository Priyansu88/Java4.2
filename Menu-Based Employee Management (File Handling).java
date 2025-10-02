
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 2L;

    private String id;
    private String name;
    private String designation;
    private double salary;

    public Employee(String id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee[ID=" + id + ", Name=" + name + ", Designation=" + designation + ", Salary=" + salary + "]";
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addEmployee(sc);
                    break;
                case "2":
                    displayAllEmployees();
                    break;
                case "3":
                    System.out.println("Exiting. Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static ArrayList<Employee> readEmployeeList() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<Employee> list = (ArrayList<Employee>) obj;
                return list;
            } else {
                System.err.println("File format incorrect. Starting a fresh list.");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void writeEmployeeList(ArrayList<Employee> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.err.println("Failed to write employees: " + e.getMessage());
        }
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Designation: ");
        String designation = sc.nextLine().trim();
        System.out.print("Enter Salary: ");
        double salary;
        try {
            salary = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary. Aborting add.");
            return;
        }

        Employee emp = new Employee(id, name, designation, salary);
        ArrayList<Employee> list = readEmployeeList();
        list.add(emp);
        writeEmployeeList(list);
        System.out.println("Employee added.");
    }

    private static void displayAllEmployees() {
        ArrayList<Employee> list = readEmployeeList();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n--- Employees ---");
        for (Employee e : list) {
            System.out.println(e);
        }
    }
}