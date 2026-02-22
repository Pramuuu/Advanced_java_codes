package cap.ums.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        // Creating Department
        System.out.println("Enter Department Name: ");
        String deptName = sc.nextLine();

        Department dept = new Department();
        dept.setName(deptName);

        // Creating the  Students
        System.out.println("How many students ");
        int studentCount = sc.nextInt();
        sc.nextLine();

        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= studentCount; i++) {
            System.out.println("Enter Student " + i + " Name:");
            String studentName = sc.nextLine();
            Student student = new Student();
            student.setName(studentName);
            // Assign Department
            student.setDepartment(dept);
            dept.getStudents().add(student);
            // Create ID Card
            System.out.println("Enter ID Card Number:");
            String cardNumber = sc.nextLine();
            IDCard card = new IDCard();
            card.setCardNumber(cardNumber);
            student.setIdCard(card);
            studentList.add(student);
        }
        // Create Courses
        System.out.println("How many courses?");
        int courseCount = sc.nextInt();
        sc.nextLine();
        List<Course> courseList = new ArrayList<>();
        for (int i = 1; i <= courseCount; i++) {
            System.out.println("Enter Course " + i + " Name:");
            String courseName = sc.nextLine();
            Course course = new Course();
            course.setCourseName(courseName);
            courseList.add(course);
            session.persist(course);
        }
        // Enrolling Students
        for (Student student : studentList) {
            System.out.println("How many courses for " + student.getName() + "?");
            int enrollCount = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < enrollCount; j++) {
                System.out.println("Enter Course Number (1 to " + courseCount + "):");
                int index = sc.nextInt() - 1;
                sc.nextLine();
                student.addCourse(courseList.get(index));
            }
        }
        session.persist(dept);
        tx.commit();
        session.close();
        factory.close();
        sc.close();
        System.out.println("Data Insertion Success");
    }
}