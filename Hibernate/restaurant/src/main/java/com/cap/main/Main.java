package com.cap.main;


import com.cap.entity.MenuItem;
import com.cap.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Restaurant Menu Management =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Price");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addItem();
                case 2 -> viewItems();
                case 3 -> updatePrice();
                case 4 -> deleteItem();
                case 5 -> {
                    HibernateUtil.getSessionFactory().close();
                    System.out.println("Application Closed");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    private static void addItem() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Category: ");
        String category = sc.next();

        System.out.print("Available (true/false): ");
        boolean available = sc.nextBoolean();

        MenuItem item = new MenuItem(name, price, category, available);
        session.save(item);
        tx.commit();

        session.close();
        System.out.println("Item Added Successfully!");
    }

    private static void viewItems() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<MenuItem> query = session.createQuery("from MenuItem", MenuItem.class);
        List<MenuItem> list = query.list();

        list.forEach(System.out::println);

        session.close();
    }

    private static void updatePrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        MenuItem item = session.get(MenuItem.class, id);
        if (item != null) {
            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();
            item.setPrice(price);

            session.update(item);
            System.out.println("Price Updated");
        } else {
            System.out.println("Item Not Found");
        }
        tx.commit();
        session.close();
    }

    private static void deleteItem() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        MenuItem item = session.get(MenuItem.class, id);
        if (item != null) {
            session.delete(item);
            System.out.println("Item Deleted");
        } else {
            System.out.println("Item Not Found");
        }

        tx.commit();
        session.close();
    }
}
