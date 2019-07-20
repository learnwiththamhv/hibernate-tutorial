package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate-employee.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			int employeeId = 3;
			// METHOD 1
			Employee employee = session.get(Employee.class, employeeId);
			session.delete(employee);

			List<Employee> employees = session.createQuery("from Employee").getResultList();
			displayEmployees(employees);

			// METHOD 2
			employeeId = 1;
			session.createQuery("delete from Employee e where e.id = " + employeeId).executeUpdate();
			employees = session.createQuery("from Employee").getResultList();
			displayEmployees(employees);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

	private static void displayEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

}
