package com.gb.app.service;

import java.time.LocalDateTime;

import com.gb.app.dao.CommentDao;
import com.gb.app.entity.Comment;
import com.gb.app.entity.Feed;
import com.gb.app.entity.User;
import com.gb.app.util.Constants;

public class CommentService implements SocialMediaService {
	
	private static CommentService commentService;
	private Integer id;
	private String msgBody;
	
	private CommentService() {}

	public static CommentService getInstance() {
		if (commentService == null)
			commentService = new CommentService();
		return commentService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		String cmdArgs = command.substring(Constants.REPLY.length() + 3);
		id = Integer.parseInt(cmdArgs.substring(0, cmdArgs.indexOf(" ")));
		msgBody = cmdArgs.substring(cmdArgs.indexOf(" ") + 1);
		
		if (command.charAt(Constants.REPLY.length() + 1) == 'f') {
			return replyOnFeed(command, authUser);
		} else {
			return replyOnComment(command, authUser);
		}
	}
	
	private String replyOnFeed(String command, User authUser) {
		Integer feedId = id;
		Feed feed = new Feed();
		feed.setId(feedId);
		
		Comment comment = new Comment(msgBody, authUser, feed, 0, LocalDateTime.now());
		CommentDao.getInstance().commentOnFeed(comment);
		return "Commented on Feed " + feedId + " successfully! Comment Id: " + comment.getId();
	}

	private String replyOnComment(String command, User authUser) {
		Integer commentId = id;
		Comment parentComment = new Comment();
		parentComment.setId(commentId);
		
		Comment childComment = new Comment(msgBody, authUser, null, null, LocalDateTime.now());
		CommentDao.getInstance().commentOnComment(parentComment, childComment);
		return "Commented on Comment " + commentId + " successfully! Comment Id: " + childComment.getId();
	}

}
