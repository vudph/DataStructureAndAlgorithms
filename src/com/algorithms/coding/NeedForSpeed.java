package com.algorithms.coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NeedForSpeed {

	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in);
		int n = StandardReader.nextInt();
		int t = StandardReader.nextInt();
		int d[] = new int[n];
		int s[] = new int[n];
		double l = Integer.MIN_VALUE;
		double r = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			d[i] = StandardReader.nextInt();
			s[i] = StandardReader.nextInt();
			l = Math.max(l, s[i]*(-1));
		}
		
		double eps = 1e-9;
		double mid = 0.0;
//		double x = 9.4362356;
//		System.out.println(eps);
		while (r - l > eps) {
			mid = (l + r)/2.0;
			
			double sum = 0;
			for (int i = 0; i < n; i++) {
				sum += d[i] / (s[i] + mid);
			}
			
//			if (sum == t) {
//				System.out.println(String.format( "%.9f", mid));
//				break;
//			}
			if (sum < t) {
				r = mid;
			} else {
				l = mid;
			}
			
//			if (mid == x) {
//				System.out.println("Found");
//				break;
//			}
//			if (mid > x) {
//				r = mid;
//			} else {
//				l = mid;
//			}
		}
		System.out.println(String.format( "%.9f", mid));
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
