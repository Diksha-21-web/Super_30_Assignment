

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FileHandler fh = new FileHandler();

        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("7. Sort Students by Marks");
            System.out.println("8. Show Topper");
            System.out.println("9. Count Total Students");
            System.out.println("10. Export to Text File");
            
            
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    if (marks < 0 || marks > 100) {
                        System.out.println("❌ Marks must be between 0–100");
                        break;
                    }

                    fh.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    fh.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    fh.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new Marks: ");
                    double newMarks = sc.nextDouble();

                    fh.updateStudent(updateId, newName, newMarks);
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    fh.deleteStudent(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;

                case 7:
    fh.sortStudentsByMarks();
    break;

case 8:
    fh.showTopper();
    break;

case 9:
    fh.countStudents();
    break;

case 10:
    fh.exportToTextFile();
    break;    

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}