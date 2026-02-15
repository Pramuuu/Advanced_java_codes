package com.cap;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Student;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1 Insert");
            System.out.println("2 Fetch");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Second Level Cache Test ⭐");
            System.out.println("6 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

            // ================= INSERT =================
            case 1: {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                try {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    Student student = new Student(id, name, course);
                    session.persist(student);

                    tx.commit();
                    System.out.println("✅ Student Inserted");

                } catch (Exception e) {
                    tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                break;
            }

            // ================= FETCH =================
            case 2: {
                Session session = factory.openSession();

                try {
                    System.out.print("Enter ID to Fetch: ");
                    int id = sc.nextInt();

                    Student student = session.get(Student.class, id);

                    if (student != null)
                        System.out.println(student);
                    else
                        System.out.println("❌ Student Not Found");

                } finally {
                    session.close();
                }
                break;
            }

            // ================= UPDATE =================
            case 3: {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                try {
                    System.out.print("Enter ID to Update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Student student = session.get(Student.class, id);

                    if (student != null) {

                        System.out.println("What do you want to update?");
                        System.out.println("1 Name");
                        System.out.println("2 Course");
                        System.out.print("Enter option: ");

                        int opt = sc.nextInt();
                        sc.nextLine();

                        if (opt == 1) {
                            System.out.print("Enter New Name: ");
                            student.setName(sc.nextLine());
                        } 
                        else if (opt == 2) {
                            System.out.print("Enter New Course: ");
                            student.setCourse(sc.nextLine());
                        } 
                        else {
                            System.out.println("Invalid Option");
                        }

                        tx.commit();
                        System.out.println("✅ Student Updated");

                    } else {
                        System.out.println("❌ Student Not Found");
                        tx.rollback();
                    }

                } catch (Exception e) {
                    tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                break;
            }

            // ================= DELETE =================
            case 4: {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                try {
                    System.out.print("Enter ID to Delete: ");
                    int id = sc.nextInt();

                    Student student = session.get(Student.class, id);

                    if (student != null) {
                        session.remove(student);
                        tx.commit();
                        System.out.println("✅ Student Deleted");
                    } else {
                        System.out.println("❌ Student Not Found");
                        tx.rollback();
                    }

                } catch (Exception e) {
                    tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
                break;
            }

            // ================= SECOND LEVEL CACHE TEST =================
            case 5: {

                System.out.print("Enter Student ID for Cache Test: ");
                int id = sc.nextInt();

                System.out.println("\n---- First Session ----");
                Session s1 = factory.openSession();
                Student st1 = s1.get(Student.class, id);

                if (st1 != null)
                    System.out.println(st1);
                else
                    System.out.println("Student Not Found");

                s1.close();

                System.out.println("\n---- Second Session ----");
                Session s2 = factory.openSession();
                Student st2 = s2.get(Student.class, id);

                if (st2 != null)
                    System.out.println(st2);
                else
                    System.out.println("Student Not Found");

                s2.close();

                break;
            }

            // ================= EXIT =================
            case 6:
                System.out.println("👋 Exiting...");
                break;

            default:
                System.out.println("❌ Invalid Choice");
            }

        } while (choice != 6);

        factory.close();
        sc.close();
    }
}


/*

package com.cap;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cap.entity.Student;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Step 1: Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;

        try {

            // Step 2: Open Session
            session = factory.openSession();

            // =========================
            // ✅ CREATE (Insert)
            // =========================
            session.beginTransaction();

            /*
            // 🔴 OLD MANUAL INPUT
            Student s1 = new Student(101, "Pramod", "AI");
            */

            // 🟢 SCANNER INPUT
/*
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Student Course: ");
            String course = sc.nextLine();

            Student s1 = new Student(id, name, course);

            session.persist(s1);
            session.getTransaction().commit();

            System.out.println("Student Inserted Successfully!");

            // =========================
            // ✅ READ (Fetch by ID)
            // =========================
            session.beginTransaction();

            System.out.print("Enter Student ID to Fetch: ");
            int fetchId = sc.nextInt();

            Student fetchedStudent = session.get(Student.class, fetchId);

            if (fetchedStudent != null) {
                System.out.println("Fetched Student : " + fetchedStudent);
            } else {
                System.out.println("Student Not Found");
            }

            session.getTransaction().commit();

            // =========================
            // ✅ UPDATE
            // =========================
            session.beginTransaction();

            System.out.print("Enter Student ID to Update: ");
            int updateId = sc.nextInt();
            sc.nextLine();

            Student updateStudent = session.get(Student.class, updateId);

            if (updateStudent != null) {
                System.out.print("Enter New Course: ");
                String newCourse = sc.nextLine();

                updateStudent.setCourse(newCourse);
                System.out.println("Student Updated Successfully!");
            } else {
                System.out.println("Student Not Found");
            }

            session.getTransaction().commit();

            // =========================
            // ✅ DELETE
            // =========================
            session.beginTransaction();

            System.out.print("Enter Student ID to Delete: ");
            int deleteId = sc.nextInt();

            Student deleteStudent = session.get(Student.class, deleteId);

            if (deleteStudent != null) {
                session.remove(deleteStudent);
                System.out.println("Student Deleted Successfully!");
            } else {
                System.out.println("Student Not Found");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (session != null) session.close();
            factory.close();
            sc.close();
        }
    }
}

*/