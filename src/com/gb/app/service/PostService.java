package com.gb.app.service;

import java.time.LocalDateTime;

import com.gb.app.dao.PostDao;
import com.gb.app.entity.Feed;
import com.gb.app.entity.User;

public class PostService implements SocialMediaService {

	private static PostService postService;

	private PostService() {
	}

	public static PostService getInstance() {
		if (postService == null)
			postService = new PostService();
		return postService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		String feedBody = command.substring(command.indexOf(" ") + 1);
		Feed feed = new Feed(feedBody, authUser, LocalDateTime.now());
		PostDao.getInstance().postFeed(feed);
		if (feed.getId() != null)
			return "Feed posted successfully. Feed Id: " + feed.getId();
		return "Feed submit failed! Contact Administrator.";
	}

}
