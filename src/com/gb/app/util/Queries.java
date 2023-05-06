package com.gb.app.util;

public class Queries {
	public static String FOLLOWING_USERS_NEWS_FEED = 
			"select y.id, y.body, y.name, y.upvotes, y.downvotes, count(c.id) comments, y.created_date "
			+ "from (select x.id, x.body, x.name, x.created_date, sum(x.up) upvotes, sum(x.down) downvotes "
			+ "	from (select fe.*, u.name, "
			+ "		case when fv.is_upvote=1 then count(fv.is_upvote) else 0 end up, "
			+ "		case when fv.is_upvote=0 then count(fv.is_upvote) else 0 end down "
			+ "		from follows fo "
			+ "		inner join feeds fe on fo.to_user_id=fe.user_id "
			+ "		inner join users u on fo.to_user_id=u.id "
			+ "		left join feed_votes fv on fe.id=fv.feed_id "
			+ "		where fo.from_user_id = ? "
			+ "		group by fe.id, fv.is_upvote "
			+ "	) x group by x.id "
			+ ") y left join comments c on y.id=c.feed_id "
			+ "group by y.id "
			+ "order by (upvotes - downvotes) desc, "
			+ "comments desc, "
			+ "y.created_date desc ";
	
	public static String GENERAL_USERS_NEWS_FEED = 
			"select y.id, y.body, y.name, y.upvotes, y.downvotes, count(c.id) comments, y.created_date "
			+ "from (select x.id, x.body, x.name, x.created_date, sum(x.up) upvotes, sum(x.down) downvotes "
			+ "	from (select fe.*, u.name, "
			+ "		case when fv.is_upvote=1 then count(fv.is_upvote) else 0 end up, "
			+ "		case when fv.is_upvote=0 then count(fv.is_upvote) else 0 end down "
			+ "		from follows fo "
			+ "		inner join feeds fe on fo.to_user_id=fe.user_id "
			+ "		inner join users u on fo.to_user_id=u.id "
			+ "		left join feed_votes fv on fe.id=fv.feed_id "
			+ "		where fo.to_user_id not in (select to_user_id from follows where from_user_id = ?) "
			+ "		group by fe.id, fv.is_upvote "
			+ "	) x group by x.id "
			+ ") y left join comments c on y.id=c.feed_id "
			+ "group by y.id "
			+ "order by (upvotes - downvotes) desc, "
			+ "comments desc, "
			+ "y.created_date desc ";
	
	public static String FEED_COMMENTS = 
			"select x.id, x.body, x.name, sum(x.up) upvotes, sum(x.down) downvotes, x.created_date "
			+ "from (select c.id, c.body, c.created_date, u.name, "
			+ "	case when cv.is_upvote=1 then count(cv.is_upvote) else 0 end up, "
			+ "	case when cv.is_upvote=0 then count(cv.is_upvote) else 0 end down "
			+ "	from comments c "
			+ "	inner join users u on c.user_id=u.id "
			+ "	left join comment_votes cv on c.id=cv.comment_id "
			+ "	where c.feed_id = ? "
			+ "	group by c.id, cv.is_upvote "
			+ ") x group by x.id "
			+ "order by x.created_date desc";
}
