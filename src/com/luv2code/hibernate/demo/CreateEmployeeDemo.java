package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate-employee.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			Employee theEmployee1 = new Employee("John", "Beck", "FPT");
			Employee theEmployee2 = new Employee("Mary", "Public", "VNPT");
			Employee theEmployee3 = new Employee("David", "Trump", "SPT");

			session.save(theEmployee1);
			session.save(theEmployee2);
			session.save(theEmployee3);

			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

}
