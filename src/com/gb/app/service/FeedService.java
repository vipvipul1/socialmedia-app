package com.gb.app.service;

import com.gb.app.entity.User;

public class FeedService implements SocialMediaService {
	
	private static FeedService feedService;

	public static FeedService getInstance() {
		if (feedService == null)
			feedService = new FeedService();
		return feedService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
