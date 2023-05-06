package com.gb.app.service;

import java.util.List;

import com.gb.app.dao.NewsFeedDao;
import com.gb.app.entity.User;
import com.gb.app.util.Constants;
import com.gb.app.util.MediaUtil;
import com.gb.app.vo.CommentVO;
import com.gb.app.vo.FeedVO;

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
		List<FeedVO> followingFeeds = NewsFeedDao.getInstance().getNewsFeedFollowing(authUser, Constants.MAX_FEED_SIZE);
		if (followingFeeds.size() < Constants.MAX_FEED_SIZE) {
			List<FeedVO> generalFeeds = NewsFeedDao.getInstance()
					.getNewsFeedGeneral(authUser, Constants.MAX_FEED_SIZE - followingFeeds.size());
			followingFeeds.addAll(generalFeeds);
		}
		NewsFeedDao.getInstance().setAllFeedsComments(followingFeeds);
		String feedsResponse = generateFeedsResponse(followingFeeds);
		return feedsResponse;
	}

	private String generateFeedsResponse(List<FeedVO> followingFeeds) {
		StringBuilder feedsResponse = new StringBuilder("");
		for (int i = 0; i < followingFeeds.size(); i++) {
			FeedVO curFeed = followingFeeds.get(i);
			StringBuilder curFeedRes = new StringBuilder("");
			
			// Adding Feed attributes - feedId, postedBy
			curFeedRes.append("#" + curFeed.getFeedId() + " ");
			String feedSpaces = MediaUtil.getSpaces(curFeedRes.length());
			curFeedRes.append(curFeed.getPostedBy() + "\n");

			// Adding feedBody within a window of Constants.MAX_WINDOW_SIZE
			int index = 0, feedLength = curFeed.getFeedBody().length();
			while (index < feedLength) {
				int maxLength = Math.min(index + Constants.MAX_WINDOW_SIZE, feedLength);
				String curRow = curFeed.getFeedBody().substring(index, maxLength);
				curFeedRes.append(feedSpaces + curRow + "\n");
				index += Constants.MAX_WINDOW_SIZE;
			}

			// Adding count of upVotes, downVotes and comments for current Feed
			curFeedRes.append(feedSpaces);
			curFeedRes.append("UpVotes: " + curFeed.getUpVotesCount() + " | ");
			curFeedRes.append("DownVotes: " + curFeed.getDownVotesCount() + " | ");
			curFeedRes.append("Comments: " + curFeed.getCommentsCount() + "\n");
			
			// Adding comments
			curFeedRes.append(feedSpaces);
			curFeedRes.append("Comments:\n");
			String commentSpaces = feedSpaces + MediaUtil.getSpaces(Constants.TAB_SIZE);
			for (CommentVO comment: curFeed.getComments()) {
				// Adding Comment attributes - commentId, postedBy
				curFeedRes.append(commentSpaces);
				String commentIdTag = "#" + comment.getCommentId() + " ";
				curFeedRes.append(commentIdTag);
				curFeedRes.append(comment.getCommentedBy() + "\n");
				String commentAttrSpaces = commentSpaces + MediaUtil.getSpaces(commentIdTag.length());
				
				// Adding commentBody within a window of Constants.MAX_WINDOW_SIZE
				int idx = 0, commentLength = comment.getCommentBody().length();
				while (idx < commentLength) {
					int maxLength = Math.min(idx + Constants.MAX_WINDOW_SIZE, commentLength);
					String curRow = comment.getCommentBody().substring(idx, maxLength);
					curFeedRes.append(commentAttrSpaces + curRow + "\n");
					idx += Constants.MAX_WINDOW_SIZE;
				}
				
				// Adding count of upVotes, downVotes and comments for current Comment
				curFeedRes.append(commentAttrSpaces);
				curFeedRes.append("UpVotes: " + comment.getUpVotesCount() + " | ");
				curFeedRes.append("DownVotes: " + comment.getDownVotesCount() + "\n");
			}
			
			feedsResponse.append(curFeedRes);
		}
		
		return feedsResponse.toString();
	}

}
