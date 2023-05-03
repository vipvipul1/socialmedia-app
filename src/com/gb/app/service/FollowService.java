package com.gb.app.service;

import com.gb.app.dao.FollowDao;
import com.gb.app.entity.User;

public class FollowService implements SocialMediaService {
	
	private static FollowService followService;
	
	private FollowService() {}

	public static FollowService getInstance() {
		if (followService == null)
			followService = new FollowService();
		return followService;
	}

	@Override
	public String executeCmd(String command, User fromUser) {
		Integer toUserId = Integer.parseInt(command.split(" ")[1]);
		
		if (fromUser.getId() == toUserId)
			return "Sorry, you cannot follow yourself!";
		User toUser = new User();
		toUser.setId(toUserId);
		
		FollowDao.getInstance().followUser(fromUser, toUser);
		return "Followed user " + toUserId + " successfully!";
	}

}
