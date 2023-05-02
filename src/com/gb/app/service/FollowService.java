package com.gb.app.service;

import com.gb.app.entity.User;

public class FollowService implements SocialMediaService {
	
	private static FollowService followService;

	public static FollowService getInstance() {
		if (followService == null)
			followService = new FollowService();
		return followService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
