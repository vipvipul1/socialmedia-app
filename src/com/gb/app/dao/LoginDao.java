package com.gb.app.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.User;

public class LoginDao {
	
	private static LoginDao loginDao;
	
	private LoginDao() {}

	public static LoginDao getInstance() {
		if (loginDao == null)
			loginDao = new LoginDao();
		return loginDao;
	}

	public User loginUser(String phone, String email, String password) {
		User user = null;
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			NativeQuery<User> query = session.createNativeQuery(
					"select * from users where (phone = ? or email = ?) and password = ?", User.class);
			query.setParameter(1, phone);
			query.setParameter(2, email);
			query.setParameter(3, password);
			user = query.getSingleResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in LoginDao :: loginUser :: " + e.getMessage());
		} finally {
			session.close();
		}
		
		return user;
	}
	
}
