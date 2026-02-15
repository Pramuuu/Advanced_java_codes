package com.realcap.entity;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OnetoOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {
            System.out.println("\n1. Add Person & Passport");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Person Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Passport Number: ");
                    String passportNumber = sc.nextLine();

                    System.out.print("Enter Country: ");
                    String country = sc.nextLine();

                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    Passport passport = new Passport(passportNumber, country);
                    Person person = new Person(name, passport);

                    session.persist(person);

                    tx.commit();
                    session.close();

                    System.out.println("Person and Passport saved successfully!");
                    break;

                case 2:
                    factory.close();
                    sc.close();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}



