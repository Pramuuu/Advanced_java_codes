package com.company.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.company.model.Employee;
import com.company.util.HibernateUtil;

public class EmployeeDAO {

    // CREATE
    public void saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            System.out.println("Employee Saved Successfully!");

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    // READ
    public Employee getEmployeeById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    // UPDATE
    public void updateSalary(int id, double newSalary) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Employee emp = session.get(Employee.class, id);

            if (emp != null) {
                emp.setSalary(newSalary);
                session.update(emp);
                transaction.commit();
                System.out.println("Salary Updated Successfully!");
            } else {
                System.out.println("Employee Not Found!");
            }

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteEmployee(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Employee emp = session.get(Employee.class, id);

            if (emp != null) {
                session.delete(emp);
                transaction.commit();
                System.out.println("Employee Deleted Successfully!");
            } else {
                System.out.println("Employee Not Found!");
            }

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}