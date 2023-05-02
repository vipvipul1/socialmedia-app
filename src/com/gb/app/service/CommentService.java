package com.gb.app.service;

import com.gb.app.entity.User;

public class CommentService implements SocialMediaService {
	
	private static CommentService commentService;

	public static CommentService getInstance() {
		if (commentService == null)
			commentService = new CommentService();
		return commentService;
	}

	@Override
	public String executeCmd(String[] cmdArgs, User authUser) {
		return null;
	}

}
