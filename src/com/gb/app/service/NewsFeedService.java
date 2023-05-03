package com.gb.app.service;

import com.gb.app.entity.User;

public class NewsFeedService implements SocialMediaService {

	private static NewsFeedService newsFeedService;

	private NewsFeedService() {
	}

	public static NewsFeedService getInstance() {
		if (newsFeedService == null)
			newsFeedService = new NewsFeedService();
		return newsFeedService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		return null;
	}

}
