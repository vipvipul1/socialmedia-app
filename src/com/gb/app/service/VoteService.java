package com.gb.app.service;

import com.gb.app.entity.User;

public class VoteService implements SocialMediaService {
	
	private static VoteService voteService;

	public static VoteService getInstance() {
		if (voteService == null)
			voteService = new VoteService();
		return voteService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
