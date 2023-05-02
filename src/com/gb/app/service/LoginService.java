package com.gb.app.service;

import com.gb.app.entity.User;

public class LoginService implements SocialMediaService {
	
	private static LoginService loginService;

	public static LoginService getInstance() {
		if (loginService == null)
			loginService = new LoginService();
		return loginService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
