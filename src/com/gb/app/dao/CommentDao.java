package com.gb.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.Comment;

public class CommentDao {

	private static CommentDao commentDao;

	private CommentDao() {
	}

	public static CommentDao getInstance() {
		if (commentDao == null)
			commentDao = new CommentDao();
		return commentDao;
	}

	public void commentOnFeed(Comment comment) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in CommentDao :: commentOnFeed :: " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void commentOnComment(Comment parentComment, Comment childComment) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			parentComment = session.get(Comment.class, parentComment.getId());
			childComment.setFeed(parentComment.getFeed());
			childComment.setLevel(parentComment.getLevel() + 1);

			session.save(childComment);

			List<Comment> childComments = new ArrayList<>();
			childComments.add(childComment);
			parentComment.setChildComments(childComments);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in CommentDao :: commentOnComment :: " + e.getMessage());
		} finally {
			session.close();
		}
	}

}
