package com.algorithms.codility;

//https://codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/

public class MissingElement {

	public static void main(String[] args) {
		int A[] = {2, 3, 1};
		System.out.println(solution(A));
	}

	private static int solution(int[] A) {
		int n = A.length;
		int sum = (int) ((n / 2.0)*(n + 1) + (n + 1));
		int sumArray = 0;
		for (int i = 0; i < A.length; i++) {
			sumArray += A[i];
		}
        return sum - sumArray;
	}
}
