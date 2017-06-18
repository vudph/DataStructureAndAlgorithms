package com.algorithms.codility;

//https://codility.com/programmers/lessons/1-iterations/binary_gap/

public class BinaryGap {

	public static void main(String[] args) {
		System.out.println(solution(1024));
	}
	
	private static int solution(int n) {
		String bin = Integer.toBinaryString(n);
//		System.out.println(bin);
		int idx = bin.indexOf("1");
//		System.out.println(idx);
		int longestGap = 0;
		while (idx != -1) {
			int idxFrom = bin.indexOf("1", idx + 1);
			if (idxFrom == -1)
				break;
			longestGap = Math.max(longestGap, idxFrom - idx);
//			System.out.println(idxFrom);
			idx = idxFrom;
		}
		return longestGap == 0 ? 0 : longestGap - 1;
	}

}
