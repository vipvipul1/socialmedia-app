package com.gb.app.util;

public class MediaUtil {

	public static String getCommandAction(String command) {
		if (command.indexOf(" ") == -1)
			return command;
		return command.substring(0, command.indexOf(" "));
	}
}
