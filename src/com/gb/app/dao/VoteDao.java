package com.gb.app.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.CommentVote;
import com.gb.app.entity.FeedVote;

public class VoteDao {

	private static VoteDao voteDao;

	private VoteDao() {
	}

	public static VoteDao getInstance() {
		if (voteDao == null)
			voteDao = new VoteDao();
		return voteDao;
	}

	public void voteFeed(FeedVote feedVote) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			TypedQuery<FeedVote> query = 
					session.createNativeQuery("select * from feed_votes where feed_id = ? and user_id = ?", FeedVote.class);
			query.setParameter(1, feedVote.getFeed().getId());
			query.setParameter(2, feedVote.getUser().getId());
			FeedVote feedVotePersist = query.getSingleResult();
			
			if (feedVotePersist.getIsUpVote() != feedVote.getIsUpVote()) {
				feedVotePersist.setIsUpVote(feedVote.getIsUpVote());
				feedVote.setId(feedVotePersist.getId());
			} else {
				session.delete(feedVotePersist);
			}
			
			session.getTransaction().commit();
		} catch (NoResultException e) {
			session.save(feedVote);
		} catch (Exception e) {
			System.out.println("Exception in VoteDao :: voteFeed :: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void voteComment(CommentVote commentVote) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			TypedQuery<CommentVote> query = 
					session.createNativeQuery("select * from comment_votes where comment_id = ? and user_id = ?", CommentVote.class);
			query.setParameter(1, commentVote.getComment().getId());
			query.setParameter(2, commentVote.getUser().getId());
			CommentVote commentVotePersist = query.getSingleResult();
			
			if (commentVotePersist.getIsUpVote() != commentVote.getIsUpVote()) {
				commentVotePersist.setIsUpVote(commentVote.getIsUpVote());
				commentVote.setId(commentVotePersist.getId());
			} else {
				session.delete(commentVotePersist);
			}
			
			session.getTransaction().commit();
		} catch (NoResultException e) {
			session.save(commentVote);
		} catch (Exception e) {
			System.out.println("Exception in VoteDao :: voteComment :: " + e.getMessage());
		} finally {
			session.close();
		}
	}
}
