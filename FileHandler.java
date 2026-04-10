
import java.io.*;
import java.util.ArrayList;




public class FileHandler {

    private static final String FILE_NAME = "students.dat";

    // 🔹 Add Student
    public void addStudent(Student student) {
        ArrayList<Student> students = readAllStudents();

        // Check duplicate ID
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                System.out.println("❌ Student ID already exists!");
                return;
            }
        }

        students.add(student);
        writeToFile(students);
        System.out.println("✅ Student added successfully!");
    }

    // 🔹 Export to Text File
public void exportToTextFile() {
    ArrayList<Student> students = readAllStudents();

    if (students.isEmpty()) {
        System.out.println("No records to export.");
        return;
    }

    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));

        for (Student s : students) {
            writer.write(s.toString());
            writer.newLine();
        }

        writer.close();
        System.out.println("✅ Data exported to students.txt");

    } catch (IOException e) {
        System.out.println("Error exporting file!");
    }
}

    // 🔹 Count Students
public void countStudents() {
    ArrayList<Student> students = readAllStudents();
    System.out.println("📊 Total Students: " + students.size());
}


    // 🔹 Show Topper
public void showTopper() {
    ArrayList<Student> students = readAllStudents();

    if (students.isEmpty()) {
        System.out.println("No records found.");
        return;
    }

    Student topper = students.get(0);

    for (Student s : students) {
        if (s.getMarks() > topper.getMarks()) {
            topper = s;
        }
    }

    System.out.println("🏆 Topper:");
    System.out.println(topper);
}

    // 🔹 Sort Students by Marks (Descending)
public void sortStudentsByMarks() {
    ArrayList<Student> students = readAllStudents();

    if (students.isEmpty()) {
        System.out.println("No records found.");
        return;
    }

    students.sort((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));

    System.out.println("📊 Students Sorted by Marks:");
    for (Student s : students) {
        System.out.println(s);
    }
}

    // 🔹 View All Students
    public void viewStudents() {
        ArrayList<Student> students = readAllStudents();

        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // 🔹 Search Student
    public void searchStudent(int id) {
        ArrayList<Student> students = readAllStudents();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("✅ Student Found:");
                System.out.println(s);
                return;
            }
        }

        System.out.println("❌ Student not found!");
    }

    // 🔹 Update Student
    public void updateStudent(int id, String newName, double newMarks) {
        ArrayList<Student> students = readAllStudents();
        boolean found = false;

        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(newName);
                s.setMarks(newMarks);
                found = true;
                break;
            }
        }

        if (found) {
            writeToFile(students);
            System.out.println("✅ Student updated successfully!");
        } else {
            System.out.println("❌ Student not found!");
        }
    }

    // 🔹 Delete Student
    public void deleteStudent(int id) {
        ArrayList<Student> students = readAllStudents();
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            writeToFile(students);
            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("❌ Student not found!");
        }
    }

    // 🔹 Read All Students
    private ArrayList<Student> readAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return students;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            students = (ArrayList<Student>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            // ignore if file empty
        }

        return students;
    }

    // 🔹 Write to File
    private void writeToFile(ArrayList<Student> students) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(students);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
}