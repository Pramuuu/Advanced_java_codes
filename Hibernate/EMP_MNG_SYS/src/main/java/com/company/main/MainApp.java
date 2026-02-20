package com.company.main;

import com.company.dao.EmployeeDAO;
import com.company.model.Employee;
import com.company.util.HibernateUtil;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        // 1️ Create
        Employee emp = new Employee("Pramod", "Data Science", 60000);
        dao.saveEmployee(emp);
        int generatedId = emp.getId();
        // 2️ Read
        Employee fetched = dao.getEmployeeById(generatedId);
        System.out.println("Fetched Employee:");
        System.out.println(fetched.getName() + " - " + fetched.getDepartment() + " - " + fetched.getSalary());

        // 3️ Update
        dao.updateSalary(generatedId, 75000);

        // 4️ Delete
        dao.deleteEmployee(generatedId);

        // Close SessionFactory
        HibernateUtil.getSessionFactory().close();
    }
}