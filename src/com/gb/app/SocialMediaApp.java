package com.gb.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gb.app.entity.User;
import com.gb.app.factory.ServiceFactory;
import com.gb.app.service.SocialMediaService;
import com.gb.app.util.Constants;

public class SocialMediaApp {
	// we can also maintain user session in database. 
	private static User authUser = null;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		displayMenu();
		while (true) {
			System.out.print("Enter command: ");
			String[] cmdArgs = reader.readLine().split("");
			SocialMediaService socialMediaService = ServiceFactory.getService(cmdArgs[0]);
			if (cmdArgs.length == 0 || socialMediaService == null) {
				System.out.println("Invalid Command! Try again.");
			} else if (authUser == null && (cmdArgs[0] != Constants.LOGIN || cmdArgs[0] != Constants.SIGNUP)) {
				System.out.println("Unauthorized access! Kindly login.");			
			} else if (cmdArgs[0] == Constants.LOGOUT) {
				authUser = null;
				System.out.println("Successfully logged out!");
			} else if (cmdArgs[0] == Constants.LOGIN) {
				String output = socialMediaService.executeCmd(cmdArgs, null);
				if (output != null) {
					authUser = new User();
					authUser.setId(Integer.parseInt(output));
					System.out.println("Logged in successfully!");									
				} else {					
					System.out.println("Invalid Credentials!");									
				}
			} else {
				String output = socialMediaService.executeCmd(cmdArgs, authUser);
				System.out.println(output);
			}
		}
	}

	public static void displayMenu() {
		System.out.println("1. Signup - (signup [name] [phone] [email] [password])");
		System.out.println("2. Login - (login [phone/email] [password])");
		System.out.println("3. Post - (post [body])");
		System.out.println("4. Follow - (follow [userId])");
		System.out.println("5. Reply on Feed/Comment - (reply f/c [feedId/commentId] [message])");
		System.out.println("6. Upvote Feed/Comment - (upvote f/c [feedId/commentId])");
		System.out.println("7. Downvote Feed/Comment - (downvote f/c [feedId/commentId])");
		System.out.println("8. Show News Feed - (shownewsfeed)");
		System.out.println("9. Logout - (logout)");
	}
}
