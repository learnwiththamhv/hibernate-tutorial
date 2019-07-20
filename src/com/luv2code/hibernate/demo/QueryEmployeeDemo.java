package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class QueryEmployeeDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate-employee.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			List<Employee> employees = session.createQuery("from Employee e where e.company = 'FPT' ").getResultList();

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
