package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			// Query
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			// Display
			displayStudents(theStudents);

			// Query lastname=Beck
			theStudents = session.createQuery("from Student s where s.lastName='Beck'").getResultList();
			System.out.println("\n\nThe Student who has last name of Beck:");
			displayStudents(theStudents);

			theStudents = session.createQuery("from Student s where" + " s.firstName='Paul' or s.lastName='Beck'")
					.getResultList();
			System.out.println("\n\nStudents who has first name of Paul or last name of Beck:");
			displayStudents(theStudents);

			theStudents = session.createQuery("from Student s where s.email like '%luv2code.com'").getResultList();
			System.out.println("\n\nStudents whose email ends with luv2code.com");
			displayStudents(theStudents);

			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
