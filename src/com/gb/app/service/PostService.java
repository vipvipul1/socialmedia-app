package com.gb.app.service;

import com.gb.app.entity.User;

public class PostService implements SocialMediaService {
	
	private static PostService postService;

	public static PostService getInstance() {
		if (postService == null)
			postService = new PostService();
		return postService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
