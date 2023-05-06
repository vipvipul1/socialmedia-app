package com.gb.app.util;

public class MediaUtil {

	public static String getCommandAction(String command) {
		if (command.indexOf(" ") == -1)
			return command;
		return command.substring(0, command.indexOf(" "));
	}
	
	public static Integer getDigitsCount(Integer num) {
		int count = 0;
		while (num > 0) {
			count++;
			num = num / 10;
		}
		return count;
	}
	
	public static String getSpaces(int size) {
		StringBuilder space = new StringBuilder("");
		while (size > 0) {
			space.append(" ");
			size--;
		}
		return space.toString();
	}
}
