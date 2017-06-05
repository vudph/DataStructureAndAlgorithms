package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.codechef.com/JUNE17/problems/GOODSET
 
public class GOODSET {
 
	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in ); // connect Reader to an input stream		
        int T = StandardReader.nextInt();
        while(T-- > 0) {
        	int n = StandardReader.nextInt();
        	if (n < 3) {
        		for (int i = 1; i <= n; i++) {
        			System.out.print(i + " ");
				}
        	} else {
        		int a[] = new int[n];
        		Arrays.fill(a, 0);
        		for (int i = 0; i < n; i++) {
        			int x = 1;
        			while (!isApplicable(x, a)) {
        				x++;
        			}
        			a[i] = x;
        			System.out.print(x + " ");
				}
        	}
        	System.out.println();
        }
	}
	
	private static boolean isApplicable(int x, int a[]) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] + a[j] == x) {
					return false;
				}
			}
		}
		return true;
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
 
