package com.gb.app.dao;

import java.util.Set;

import org.hibernate.Session;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.User;

public class FollowDao {

	private static FollowDao followDao;

	private FollowDao() {
	}

	public static FollowDao getInstance() {
		if (followDao == null)
			followDao = new FollowDao();
		return followDao;
	}

	public void followUser(User fromUser, User toUser) {
		Session session = ConnectionUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			fromUser = session.get(User.class, fromUser.getId());
			Set<User> toUsersSet = fromUser.getFollowingsSet();
			if (toUsersSet.contains(toUser)) {
				toUsersSet.remove(toUser);
				toUser.setId(null);
			} else {
				toUsersSet.add(toUser);
			}

			session.getTransaction().commit();
			System.out.println("Hello");
		} catch (Exception e) {
			System.out.println("Exception in FollowDao :: followUser :: " + e.getMessage());
		} finally {
			session.close();
		}
	}

}
