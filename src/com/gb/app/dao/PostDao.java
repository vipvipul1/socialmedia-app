package com.gb.app.dao;

import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.Feed;

public class PostDao {

	private static PostDao postDao;
	
	private PostDao() {}

	public static PostDao getInstance() {
		if (postDao == null)
			postDao = new PostDao();
		return postDao;
	}

	public void postFeed(Feed feed) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(feed);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in PostDao :: postFeed :: " + e.getMessage());
		} finally {
			session.close();
		}		
	}
	
}
