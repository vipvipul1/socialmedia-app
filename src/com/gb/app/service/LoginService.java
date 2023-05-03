package com.gb.app.service;

import com.gb.app.dao.LoginDao;
import com.gb.app.entity.User;

public class LoginService implements SocialMediaService {
	
	private static LoginService loginService;
	
	private LoginService() {}

	public static LoginService getInstance() {
		if (loginService == null)
			loginService = new LoginService();
		return loginService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		String[] cmdArgs = command.split(" ");
		String phone = cmdArgs[1];
		String email = cmdArgs[1];
		String password = cmdArgs[2];
		authUser = LoginDao.getInstance().loginUser(phone, email, password);
		return authUser == null ? null : String.valueOf(authUser.getId());
	}

}
