package miniproject3;

import java.util.*;

class Student {
    int eno;
    String name;
    String sem;
    String branch;
    double percentage;

    Student(int eno, String name, String sem, String branch, double percentage) {
        this.eno = eno;
        this.name = name;
        this.sem = sem;
        this.branch = branch;
        this.percentage = percentage;
    }

    public String toString() {
        return eno + " | " + name + " | " + sem + " | " + branch + " | " + percentage;
    }
}

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        login();

        while (true) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Students");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Students by Eno");
            System.out.println("4. Update Students Branch");
            System.out.println("5. Delete Students by Eno");
            System.out.println("6. Display Sorted Students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayAll();
                case 3 -> searchStudent();
                case 4 -> updateBranch();
                case 5 -> deleteStudent();
                case 6 -> displaySorted();
                case 7 -> {
                    System.out.println("Thank you! Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    //  Login System
    static void login() {
        System.out.print("Username: ");
        String user = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();

        if (!user.equals("abcd") || !pass.equals("abcd")) {
            System.out.println("Invalid Login!");
            System.exit(0);
        }
        System.out.println("Login Successful!");
    }

    // Add Student
    static void addStudent() {
        try {
            System.out.print("Enter Eno: ");
            int eno = sc.nextInt();

            for (Student s : students) {
                if (s.eno == eno)
                    throw new Exception("Student Eno must be unique!");
            }

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Semester: ");
            String sem = sc.next();
            if (sem.isEmpty())
                throw new Exception("Semester cannot be empty!");

            System.out.print("Enter Branch: ");
            String branch = sc.next();
            if (branch.isEmpty())
                throw new Exception("Branch cannot be empty!");

            System.out.print("Enter Percentage: ");
            double per = sc.nextDouble();
            if (per <= 0)
                throw new Exception("Percentage must be positive!");

            students.add(new Student(eno, name, sem, branch, per));
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //  Display All
    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }
        System.out.println("Eno | Name | Sem | Branch | Percentage");
        for (Student s : students)
            System.out.println(s);
    }

    //  Search Student
    static void searchStudent() {
        System.out.print("Enter Eno to search: ");
        int eno = sc.nextInt();

        for (Student s : students) {
            if (s.eno == eno) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    //  Update Branch
    static void updateBranch() {
        System.out.print("Enter Eno: ");
        int eno = sc.nextInt();

        for (Student s : students) {
            if (s.eno == eno) {
                System.out.print("Enter new Branch: ");
                String branch = sc.next();
                if (branch.isEmpty()) {
                    System.out.println("Branch cannot be empty!");
                    return;
                }
                s.branch = branch;
                System.out.println("Branch updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    //  Delete Student
    static void deleteStudent() {
        System.out.print("Enter Eno to delete: ");
        int eno = sc.nextInt();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().eno == eno) {
                it.remove();
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    //  Sort Students by Name
    static void displaySorted() {
        if (students.isEmpty()) {
            System.out.println("No records to sort!");
            return;
        }

        students.stream()
                .sorted(Comparator.comparing(s -> s.name))
                .forEach(System.out::println);
    }
}

