package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.codechef.com/JUNE17/problems/XENRANK/

public class XENRANK {
 
	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in ); // connect Reader to an input stream		
        int T = StandardReader.nextInt();
        while(T-- > 0) {
        	long u = StandardReader.nextLong();
        	long v = StandardReader.nextLong();
        	System.out.println(rank(u, v));
        }
	}
	
	private static long rank(long x, long y) {
		long d = y + 2;
		long rank = 1 + (y*(y + 1)/2);
		rank = rank + x*d + (x*(x - 1)/2); //1000000002660865025
		
		return rank;
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
