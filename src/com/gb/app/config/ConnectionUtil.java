package com.gb.app.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gb.app.entity.Comment;
import com.gb.app.entity.CommentVote;
import com.gb.app.entity.Feed;
import com.gb.app.entity.FeedVote;
import com.gb.app.entity.User;

public class ConnectionUtil {
	private static SessionFactory factory;
	
	public static SessionFactory getSessionFactory() {
		if (factory == null || factory.isClosed()) {
			factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(User.class).addAnnotatedClass(Feed.class).addAnnotatedClass(Comment.class)
					.addAnnotatedClass(FeedVote.class).addAnnotatedClass(CommentVote.class).buildSessionFactory();
		}
		return factory;
	}
	
	public static void closeSessionFactory() {
		factory.close();
	}
}
