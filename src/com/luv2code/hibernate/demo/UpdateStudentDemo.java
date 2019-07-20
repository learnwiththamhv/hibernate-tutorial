package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			int studentId = 3;

			session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("Getting Student with ID: " + studentId);

			Student myStudent = session.get(Student.class, studentId);

			System.out.println("Updating student ...");

			myStudent.setFirstName("Scooby");

			session.getTransaction().commit();

			// NEW CODE

			session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("Updating John's email address ...");
			session.createQuery("update Student s set s.email='john@gmail.com' where s.firstName='John'")
					.executeUpdate();

			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}

}
