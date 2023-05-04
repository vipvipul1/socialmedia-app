package com.gb.app.service;

import java.time.LocalDateTime;

import com.gb.app.dao.VoteDao;
import com.gb.app.entity.Comment;
import com.gb.app.entity.CommentVote;
import com.gb.app.entity.Feed;
import com.gb.app.entity.FeedVote;
import com.gb.app.entity.User;
import com.gb.app.util.Constants;
import com.gb.app.util.MediaUtil;

public class VoteService implements SocialMediaService {
	
	private static VoteService voteService;
	
	private VoteService() {}

	public static VoteService getInstance() {
		if (voteService == null)
			voteService = new VoteService();
		return voteService;
	}

	@Override
	public String executeCmd(String command, User authUser) {
		String action = MediaUtil.getCommandAction(command);
		Integer id = Integer.parseInt(command.substring(action.length() + 3));
		boolean isFeed = command.charAt(action.length() + 1) == 'f';
		boolean isUpVote = Constants.UP_VOTE.equals(action) ? true : false;
		if (isFeed) {
			return voteFeed(id, isUpVote, authUser);
		} else {
			return voteComment(id, isUpVote, authUser);
		}
	}
	
	private String voteFeed(Integer feedId, Boolean isUpVote, User authUser) {
		Feed feed = new Feed();
		feed.setId(feedId);
		
		FeedVote feedVote = new FeedVote(feed, authUser, isUpVote, LocalDateTime.now());
		VoteDao.getInstance().voteFeed(feedVote);
		
		if (feedVote.getId() != null)
			return "Feed " + feedId + " voted " + (isUpVote ? "up." : "down.");
		return "Your existing " + (isUpVote ? "upvote" : "downvote") + " on Feed " + feedId + " cleared.";
	}
	
	private String voteComment(Integer commentId, Boolean isUpVote, User authUser) {
		Comment comment = new Comment();
		comment.setId(commentId);
		
		CommentVote commentVote = new CommentVote(comment, authUser, isUpVote, LocalDateTime.now());
		VoteDao.getInstance().voteComment(commentVote);
		
		if (commentVote.getId() != null)
			return "Comment " + commentId + " voted " + (isUpVote ? "up." : "down.");
		return "Your existing " + (isUpVote ? "upvote" : "downvote") + " on Comment " + commentId + " cleared.";
	}

}
