package com.algorithms.codility;

//https://codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/

public class OddOccurrencesInArray {

	public static void main(String[] args) {
		int A[] = {9, 3, 9, 5, 9, 9, 5};
		System.out.println(solution(A, A.length));
	}

	private static int solution(int A[], int N) {
		int res = A[0];
		for (int i = 1; i < N; i++) {
			res ^= A[i];
		}
		return res;
	}
}
