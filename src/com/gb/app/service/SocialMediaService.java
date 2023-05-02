package com.gb.app.service;

import com.gb.app.entity.User;

public interface SocialMediaService {
	
	public String executeCmd(String[] cmdArgs, User authUser);

}
