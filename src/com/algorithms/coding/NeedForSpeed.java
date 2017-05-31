package com.algorithms.coding;

public class NeedForSpeed {

	public static void main(String[] args) {
		int x = 7;
		int A[] = {1, 4, 5, 6, 9};
		int n = A.length;
		int l = 0;
		int r = n-1;
		
		while (l <= r) {
			int midId = (l + r) / 2;
			int mid = A[midId];
			if (mid == x) {
				System.out.println("Found at: " + midId);
				break;
			}
			if (mid < x) {
				l = midId + 1;
			} else {
				r = midId - 1;
			}
		}
		if (l >= r) {
			System.out.println("Not found");
		}
	}

}
