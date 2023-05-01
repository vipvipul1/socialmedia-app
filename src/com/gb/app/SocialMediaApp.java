package com.gb.app;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gb.app.user.entity.User;

public class SocialMediaApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			User user = new User("Vipul", "7033341667", "vipul@gmail.com", "abcd", LocalDateTime.now());

			session.beginTransaction();

			int id = (Integer) session.save(user);

			session.getTransaction().commit();
			System.out.println(id);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			factory.close();
		}
	}
}
