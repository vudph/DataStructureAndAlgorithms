package com.algorithms.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SecretChamber {

	public static void main(String[] args) {
//		int m = 3;
//		int n = 3;
//		String translations[] = {"a c", "b a", "a b"};
//		String words[] = {"aaa abc", "abc aaa", "acm bcm"};
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
        int n = sc.nextInt();
        long F[][] = new long[26][26];
        for (int i = 0; i < 26; i++) {
        	Arrays.fill(F[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < 26; i++) 
        	F[i][i] = 1;
        
        for (int i = 0; i < m; i++) {
			String c1 = sc.next();
			String c2 = sc.next();
			int x = c1.charAt(0) - 'a';
			int y = c2.charAt(0) - 'a';
			F[x][y] = 1;
        }
        for (int k = 0; k < 26; k++) {
        	for (int i = 0; i < 26; i++) {
        		for(int j = 0; j < 26; j++) {
        			if (F[i][j] > F[i][k] + F[k][j]) {
        				F[i][j] = F[i][k] + F[k][j];
        			}
        		}
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	String word1 = sc.next();
			String word2 = sc.next();
			if (word1.length() != word2.length()) {
				System.out.println("no");
				continue;
			}
//			boolean match[] = new boolean[word1.length()];
//			Arrays.fill(match, false);
			boolean isMatch = true;
			for (int j = 0; j < word1.length(); j++) {
				char ch1 = word1.charAt(j);
				char ch2 = word2.charAt(j);
				if (F[ch1 - 'a'][ch2 - 'a'] == Integer.MAX_VALUE) {
					isMatch = false;
					break;
				}
			}
			
			System.out.println(isMatch ? "yes" : "no");
        }
        
        
        
        
        
        
        
        
        
        
        
        
//        HashMap<Character, List<Character>> charMapping = new HashMap<Character, List<Character>>();
//        for (int i = 0; i < m; i++) {
//			String c1 = sc.next();
//			String c2 = sc.next();
//			if (charMapping.containsKey(c1.charAt(0))) {
//				List<Character> s = charMapping.get(c1.charAt(0));
//				s.add(c2.charAt(0));				
//			} else {
//				List<Character> s = new ArrayList<Character>();
//				s.add(c2.charAt(0));
//				charMapping.put(c1.charAt(0), s);
//			}
//		}
//		
//        for (int i = 0; i < n; i++) {
//        	String word1 = sc.next();
//			String word2 = sc.next();
//        	
//			if (word1.length() != word2.length()) {
//				System.out.println("no");
//				continue;
//			}
//			
//			boolean match[] = new boolean[word1.length()];
//			Arrays.fill(match, false);
//			for (int j = 0; j < word1.length(); j++) {
//				char ch1 = word1.charAt(j);
//				char ch2 = word2.charAt(j);
//				Queue<Character> queue = new LinkedList<>();
//				queue.add(ch1);
//				while(!queue.isEmpty()) {
//					char cc = queue.remove();
//					if (cc != ch2) {
//						List<Character> mappingChars = charMapping.get(cc);
//						if (mappingChars != null) {
//							for (Character c : mappingChars) {
//								queue.add(c);
//							}
//						}
//					} else {
//						match[j] = true;
//						break;
//					}
//				}
//				if (!match[j]) {
//					break;
//				}
//			}
//			boolean ret = true;
//			for (boolean b : match) {
//				if (!b) {
//					ret = false;
//					break;
//				}
//			}
//			System.out.println(ret ? "yes" : "no");
//        }
	}
	
	private static String[] matchWords(int m, int n, String[] translations, String[] words) {
		HashMap<Character, List<Character>> charMapping = new HashMap<Character, List<Character>>();
		for (String ch : translations) {
			String c[] = ch.split(" ");
			if (charMapping.containsKey(c[0].charAt(0))) {
				List<Character> s = charMapping.get(c[0].charAt(0));
				s.add(c[1].charAt(0));				
			} else {
				List<Character> s = new ArrayList<Character>();
				s.add(c[1].charAt(0));
				charMapping.put(c[0].charAt(0), s);
			}
		}
		String res[] = new String[n];
		for (int i = 0; i < words.length; i++) {
			String word[] = words[i].split(" ");
			if (word[0].length() != word[1].length()) {
				res[i] = "no";
				continue;
			}
			boolean match[] = new boolean[word[0].toCharArray().length];
			Arrays.fill(match, false);
			for (int j = 0; j < word[0].toCharArray().length; j++) {
				char ch1 = word[0].charAt(j);
				char ch2 = word[1].charAt(j);
				Queue<Character> stack = new LinkedList<>();
				stack.add(ch1);
				while(!stack.isEmpty()) {
					char cc = stack.remove();
					if (cc != ch2) {
						List<Character> mappingChars = charMapping.get(cc);
						if (mappingChars != null) {
							for (Character c : mappingChars) {
								stack.add(c);
							}
						}
					} else {
						match[j] = true;
						break;
					}
				}
				if (!match[j]) {
					break;
				}
			}
			boolean ret = true;
			for (boolean b : match) {
				if (!b) {
					ret = false;
					break;
				}
			}
			res[i] = ret ? "yes" : "no";
		}
		return res;
	}

}
