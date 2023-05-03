package com.gb.app.dao;

import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.User;

public class SignupDao {
	
	private static SignupDao signupDao;
	
	private SignupDao() {}

	public static SignupDao getInstance() {
		if (signupDao == null)
			signupDao = new SignupDao();
		return signupDao;
	}

	public void signupUser(User user) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in SignupDao :: signupUser :: " + e.getMessage());
		} finally {
			session.close();
		}
	}
	
}
