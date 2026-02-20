package com.cap.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ManyToManyMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("student-course-pu");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // ===== Student Input =====
        System.out.print("Enter Student Name: ");
        String studentName = sc.nextLine();

        // ===== Course Count =====
        System.out.print("Enter Number of Courses: ");
        int courseCount = sc.nextInt();
        sc.nextLine();

        List<Course> courseList = new ArrayList<>();

        // ===== Course Input Loop =====
        for (int i = 1; i <= courseCount; i++) {

            System.out.print("Enter Course " + i + " Name: ");
            String courseName = sc.nextLine();

            Course course = new Course(courseName);

            em.persist(course);   // Save course first
            courseList.add(course);
        }

        // ===== Create Student =====
        Student student = new Student(studentName, courseList);

        em.persist(student);

        tx.commit();

        em.close();
        emf.close();

        System.out.println("Student and Courses Saved Successfully!");
    }
}