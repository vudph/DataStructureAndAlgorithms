package com.algorithms.coding;

import java.util.Arrays;

/*
 * https://community.topcoder.com/stat?c=problem_statement&pm=10355&rd=13803
 */
public class EndlessStringMachine {

	public static void main(String[] args) {
		String input = "top";
		String program = "$coder";
		int s = 1;
		int min = 1;
		int max = 20;
		System.out.println(getFragment(input, program, s, min, max));
	}

	private static String getFragment(String input, String program, int s, int min, int max) {
		for(int i = 0; i < s; i++) {
			input = program.replace("$", input);
		}
		if ((max - (min - 1)) > input.length()) {
			int spaces = max - (min - 1) - input.length();
			char[] chars = new char[spaces];
			Arrays.fill(chars, '-');
			return input.concat(new String(chars));
		}
		return input.substring(min-1, max);
	}
}
