package com.gb.app.dao;

import com.gb.app.entity.User;

public class SignupDao {
	
	private static SignupDao signupDao;

	public static SignupDao getInstance() {
		if (signupDao == null)
			signupDao = new SignupDao();
		return signupDao;
	}

	public Integer signupUser(User authUser) {
		
		return null;
	}
	
}
