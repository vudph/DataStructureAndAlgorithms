package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.codechef.com/JUNE17/problems/NEO01
public class Main {

	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in); // connect Reader to an input stream		
        int T = StandardReader.nextInt();
        while(T-- > 0) {
        	int n = StandardReader.nextInt();
//        	long A[] = new long[n];
        	
        	int posCount = 0;
        	long posSum = 0;
        	long navSum = 0;
        	for (int i = 0; i < n; i++) {
				long h = StandardReader.nextLong();
				if (h > 0) {
					posCount++;
					posSum = posSum + h;
				} else {
					navSum = navSum + h;
				}
			}
        	
        	long sum = (posSum * posCount) + navSum;
        	
        	System.out.println(sum);
        }
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
