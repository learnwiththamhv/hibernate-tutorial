package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class ReadEmployeeDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate-employee.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			int employeeId = 2;

			Employee theEmployee = session.get(Employee.class, employeeId);

			System.out.println("Reading Employee with ID = 2 from DB:");
			System.out.println(theEmployee);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

}
