package com.gb.app.factory;

import com.gb.app.service.CommentService;
import com.gb.app.service.FeedService;
import com.gb.app.service.FollowService;
import com.gb.app.service.LoginService;
import com.gb.app.service.PostService;
import com.gb.app.service.SignupService;
import com.gb.app.service.SocialMediaService;
import com.gb.app.service.VoteService;
import com.gb.app.util.Constants;

public class ServiceFactory {
	
	public static SocialMediaService getService(String command) {
		SocialMediaService service = null;
		if (Constants.SIGNUP.equals(command)) {
			service = SignupService.getInstance();
		} else if (Constants.LOGIN.equals(command)) {
			service = LoginService.getInstance();			
		} else if (Constants.POST.equals(command)) {
			service = PostService.getInstance();			
		} else if (Constants.FOLLOW.equals(command)) {
			service = FollowService.getInstance();			
		} else if (Constants.REPLY.equals(command)) {
			service = CommentService.getInstance();			
		} else if (Constants.UP_VOTE.equals(command) || Constants.DOWN_VOTE.equals(command)) {
			service = VoteService.getInstance();			
		} else if (Constants.SHOW_NEWS_FEED.equals(command)) {
			service = FeedService.getInstance();			
		}
		return service;
	}

}
