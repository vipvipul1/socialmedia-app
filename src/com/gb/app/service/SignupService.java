package com.gb.app.service;

import java.time.LocalDateTime;

import com.gb.app.dao.SignupDao;
import com.gb.app.entity.User;

public class SignupService implements SocialMediaService {
	
	private static SignupService signupService;

	public static SignupService getInstance() {
		if (signupService == null)
			signupService = new SignupService();
		return signupService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		String name = cmdArgs[1];
		String phone = cmdArgs[2];
		String email = cmdArgs[3];
		String password = cmdArgs[4];
		authUser = new User(name, phone, email, password, LocalDateTime.now());
		Integer signupId = SignupDao.getInstance().signupUser(authUser);
		if (signupId != null)
			return String.valueOf(signupId);
		return null;
	}

}
