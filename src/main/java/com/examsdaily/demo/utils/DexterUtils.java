package com.examsdaily.demo.utils;

import java.util.regex.Pattern;

/**
 * @author zentere
 *
 */
public class DexterUtils {

	public static String[] onStringSplit(String question) {
		String[] questions = question.split(Pattern.quote(")"));
		String[] responds = new String[2];
		try {
			System.out.println(questions[0]);
			responds[0] = questions[0];
		} catch (ArrayIndexOutOfBoundsException e1) {
			responds[0] = "";
			return responds;
		}
		try {
			System.out.println(questions[1]);
			responds[1] = questions[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			responds[1] = "";
			return responds;
		}
		return responds;
	}

	public static void main(String[] args) {
		String question2 = "Q.1)If the range of a function is a singleton set, then it is ";
		String[] questions = question2.split(Pattern.quote(")"));
		System.out.println(questions[0]);
		System.out.println(questions[1]);
	}
}