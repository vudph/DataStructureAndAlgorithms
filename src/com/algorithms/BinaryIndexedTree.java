package com.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BinaryIndexedTree {

	public static void main(String[] args) throws IOException {
//		StandardReader.init(System.in);
//        int n = StandardReader.nextInt();
//        int A[] = new int[n];
        int A[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
//        2, 1, 1, 3, 2, 3,  4,  5, 6, 7, 8, 9
//        2  3  4  7  9  12  16  21 27 34 42 51 
        int BITree[] = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
//			A[i] = StandardReader.nextInt();
			updateBIT(BITree, A.length, i, A[i]);
		}
        System.out.println("sum of first 4 elements is " + getSum(BITree, 4));
        for (int i = 0; i < A.length; i++) {
        	System.out.print((getSum(BITree, i)) + " ");
		}
        System.out.println();
        System.out.println("sum of all elements in range [2, 7] is " + (getSum(BITree, 7) - getSum(BITree, 2-1)));
	}

	// Updates a node in Binary Index Tree (BITree) at given index
	// in BITree.  The given value 'val' is added to BITree[i] and 
	// all of its ancestors in tree.
	private static void updateBIT(int BITree[], int n, int index, int val) {
		// index in BITree[] is 1 more than the index in arr[]
		index = index + 1;

		// Traverse all ancestors and add 'val'
		while (index <= n) {
			// Add 'val' to current node of BI Tree
			BITree[index] += val;

			// Update index to that of parent in update View
			index += index & (-index);
		}
	}
	
	/*  
	    n  --> No. of elements present in input array.   
	    BITree[0..n] --> Array that represents Binary Indexed Tree.
	    arr[0..n-1]  --> Input array for whic prefix sum is evaluated. 
	*/
	// Returns sum of arr[0..index]. This function assumes
	// that the array is preprocessed and partial sums of
	// array elements are stored in BITree[].
	private static int getSum(int BITree[], int index) {
		int sum = 0; // Iniialize result

		// index in BITree[] is 1 more than the index in arr[]
		index = index + 1;

		// Traverse ancestors of BITree[index]
		while (index > 0) {
			// Add current element of BITree to sum
			sum += BITree[index];

			// Move index to parent node in getSum View
			index -= index & (-index);
		}
		return sum;
	}

	static class StandardReader {
		static BufferedReader reader;
	    static StringTokenizer tokenizer;
 
	    /** call this method to initialize reader for InputStream */
	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                     new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }
 
	    /** get next word */
	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            //TODO add check for eof if necessary
	            tokenizer = new StringTokenizer(
	                   reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }
 
	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
	    
	    static long nextLong() throws IOException {
	        return Long.parseLong( next() );
	    }
		
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }
	}
}
