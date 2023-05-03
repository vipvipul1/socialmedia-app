package com.gb.app.service;

import java.time.LocalDateTime;

import com.gb.app.dao.SignupDao;
import com.gb.app.entity.User;

public class SignupService implements SocialMediaService {
	
	private static SignupService signupService;
	
	private SignupService() {}

	public static SignupService getInstance() {
		if (signupService == null)
			signupService = new SignupService();
		return signupService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		String[] cmdArgs = command.split(" ");
		String name = cmdArgs[1];
		String phone = cmdArgs[2];
		String email = cmdArgs[3];
		String password = cmdArgs[4];
		User user = new User(name, phone, email, password, LocalDateTime.now());
		SignupDao.getInstance().signupUser(user);
		if (user.getId() == null)
			return "Signup failed! Contact administrator";
		return "User signed up successfully. User Id: " + user.getId();
	}

}
