import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cap.entity.Department;
import com.cap.entity.Employee;
import com.cap.util.HibernateUtil;

public class ManyToOneMainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {

            // Department Input
            System.out.print("Enter Department Name: ");
            String deptName = sc.nextLine();

            Department dept = new Department(deptName);
            session.persist(dept);

            // Number of Employees
            System.out.print("Enter Number of Employees: ");
            int n = sc.nextInt();
            sc.nextLine();

            for(int i = 1; i <= n; i++) {

                System.out.print("Enter Employee Name " + i + ": ");
                String empName = sc.nextLine();

                Employee emp = new Employee(empName, dept);
                session.persist(emp);
            }

            tx.commit();
            System.out.println("Data Saved Successfully");

        } catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sc.close();
        }
    }
}