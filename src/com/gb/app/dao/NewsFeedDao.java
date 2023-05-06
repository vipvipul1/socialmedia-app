package com.gb.app.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.Comment;
import com.gb.app.entity.Feed;
import com.gb.app.entity.User;
import com.gb.app.util.Constants;
import com.gb.app.util.Queries;
import com.gb.app.vo.CommentVO;
import com.gb.app.vo.FeedVO;

public class NewsFeedDao {

	private static NewsFeedDao newsFeedDao;

	private NewsFeedDao() {
	}

	public static NewsFeedDao getInstance() {
		if (newsFeedDao == null)
			newsFeedDao = new NewsFeedDao();
		return newsFeedDao;
	}

	public List<FeedVO> getNewsFeedFollowing(User fromUser, int maxFeedCount) {
		List<FeedVO> feedList = new ArrayList<>();
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			User fromUserPersist = session.get(User.class, fromUser.getId());
			if (fromUserPersist.getFollowingsSet().isEmpty()) {
				return feedList;
			}
			StringBuilder queryBuilder = new StringBuilder(Queries.FOLLOWING_USERS_NEWS_FEED);
			queryBuilder.append("limit 0," + maxFeedCount + ";");
			Query query = session.createNativeQuery(queryBuilder.toString());
			query.setParameter(1, fromUser.getId());

			List<Object[]> daoResult = query.getResultList();
			feedList = buildFeedList(daoResult);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in NewsFeedDao :: getNewsFeedFollowing :: " + e.getMessage());
		} finally {
			session.close();
		}
		return feedList;
	}

	public List<FeedVO> getNewsFeedGeneral(User fromUser, int maxFeedCount) {
		List<FeedVO> feedList = new ArrayList<>();
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			StringBuilder queryBuilder = new StringBuilder(Queries.GENERAL_USERS_NEWS_FEED);
			queryBuilder.append("limit 0," + maxFeedCount + ";");
			Query query = session.createNativeQuery(queryBuilder.toString());
			query.setParameter(1, fromUser.getId());

			List<Object[]> daoResult = query.getResultList();
			feedList = buildFeedList(daoResult);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in NewsFeedDao :: getNewsFeedGeneral :: " + e.getMessage());
		} finally {
			session.close();
		}
		return feedList;
	}

	private List<FeedVO> buildFeedList(List<Object[]> daoResult) {
		List<FeedVO> feedList = new ArrayList<>();
		if (!daoResult.isEmpty()) {
			for (Object[] rowCols : daoResult) {
				FeedVO feedVO = new FeedVO();
				feedVO.setFeedId((Integer) rowCols[0]);
				feedVO.setFeedBody((String) rowCols[1]);
				feedVO.setPostedBy((String) rowCols[2]);
				feedVO.setUpVotesCount(((BigDecimal) rowCols[3]).longValue());
				feedVO.setDownVotesCount(((BigDecimal) rowCols[4]).longValue());
				feedVO.setCommentsCount(((BigInteger) rowCols[5]).longValue());

				LocalDateTime postDate = ((Date) rowCols[6]).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				long duration = Duration.between(postDate, LocalDateTime.now()).toMillis();
				String prettyDate = DurationFormatUtils.formatDurationWords(duration, true, true) + " ago";
				feedVO.setPostDate(prettyDate);

				feedList.add(feedVO);
			}
		}
		return feedList;
	}

	public void setAllFeedsComments(List<FeedVO> followingFeeds) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			for (FeedVO feedVo: followingFeeds) {
				List<CommentVO> commentsList = new ArrayList<>();
				
				StringBuilder queryStr = new StringBuilder(Queries.FEED_COMMENTS);
				queryStr.append(" limit 0," + Constants.MAX_COMMENT_SIZE);
				Query query = session.createNativeQuery(queryStr.toString());
				query.setParameter(1, feedVo.getFeedId());
				List<Object[]> comments = query.getResultList();
				for (Object[] rowCols: comments) {
					CommentVO commentVO = new CommentVO();
					commentVO.setCommentId((Integer) rowCols[0]);
					commentVO.setCommentBody((String) rowCols[1]);
					commentVO.setCommentedBy((String) rowCols[2]);
					commentVO.setUpVotesCount(((BigDecimal) rowCols[3]).longValue());
					commentVO.setDownVotesCount(((BigDecimal) rowCols[4]).longValue());
					
					LocalDateTime postDate = ((Date) rowCols[5]).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					long duration = Duration.between(postDate, LocalDateTime.now()).toMillis();
					String prettyDate = DurationFormatUtils.formatDurationWords(duration, true, true) + " ago";
					commentVO.setCommentDate(prettyDate);
					
					// add child comments based on Constants.MAX_CHILD_COMMENT_SIZE
					
					commentsList.add(commentVO);
				}
				feedVo.setComments(commentsList);
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in NewsFeedDao :: getNewsFeedGeneral :: " + e.getMessage());
		} finally {
			session.close();
		}
	}
}
