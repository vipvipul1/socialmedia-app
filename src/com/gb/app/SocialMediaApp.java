package com.gb.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gb.app.config.ConnectionUtil;
import com.gb.app.entity.User;
import com.gb.app.factory.ServiceFactory;
import com.gb.app.service.SocialMediaService;
import com.gb.app.util.Constants;
import com.gb.app.util.MediaUtil;

public class SocialMediaApp {
	// we can also maintain user session in database.
	private static User authUser = null;

	public static void main(String[] args) throws IOException {
		ConnectionUtil.getSessionFactory();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		displayMenu();
		while (true) {
			System.out.print("Enter command: ");
			String command = reader.readLine();
			String action = MediaUtil.getCommandAction(command);
			SocialMediaService socialMediaService = ServiceFactory.getService(action);
			if (authUser != null && Constants.LOGOUT.equals(action)) {
				authUser = null;
				System.out.println("Logged out successfully!");
			} else if (action.length() == 0 || socialMediaService == null) {
				System.out.println("Invalid Command! Try again.");
			} else if (authUser == null && (!Constants.LOGIN.equals(action) && !Constants.SIGNUP.equals(action))) {
				System.out.println("Unauthorized access! Kindly login.");
			} else if (Constants.LOGIN.equals(action)) {
				String output = socialMediaService.executeCmd(command, null);
				if (output != null) {
					authUser = new User();
					authUser.setId(Integer.parseInt(output));
					System.out.println("Logged in successfully!");
				} else {
					System.out.println("Invalid Credentials!");
				}
			} else {
				String output = socialMediaService.executeCmd(command, authUser);
				System.out.println(output);
			}
		}
	}

	public static void displayMenu() {
		System.out.println("1. Signup - (signup [name] [phone] [email] [password])\n"
				+ "2. Login - (login [phone/email] [password])\n" + "3. Post - (post [body])\n"
				+ "4. Follow - (follow [userId])\n"
				+ "5. Reply on Feed/Comment - (reply f/c [feedId/commentId] [message])\n"
				+ "6. Upvote Feed/Comment - (upvote f/c [feedId/commentId])\n"
				+ "7. Downvote Feed/Comment - (downvote f/c [feedId/commentId])\n"
				+ "8. Show News Feed - (shownewsfeed)\n" + "9. Logout - (logout)");
	}
}
