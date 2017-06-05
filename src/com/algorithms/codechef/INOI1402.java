package com.algorithms.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.codechef.com/IOIPRAC/problems/INOI1402

public class INOI1402 {

	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in ); // connect Reader to an input stream		
        int n = StandardReader.nextInt();
        int m = StandardReader.nextInt();
        long A[][] = new long[n][n];
        for (int i = 0; i < n; i++) {
			Arrays.fill(A[i], Integer.MAX_VALUE);
			A[i][i] = 0;
		}
        
        for (int i = 0; i < m; i++) {
			int x = StandardReader.nextInt();
			int y = StandardReader.nextInt();
			int z = StandardReader.nextInt();
			A[x-1][y-1] = A[y-1][x-1] = z;
		}
        boolean F[] = new boolean[n];
        Arrays.fill(F, false);
        long D[] = new long[n];
        int Trace[] = new int[n];
        for (int i = 0; i < n; i++) {
			D[i] = A[0][i];
			Trace[i] = 0;
			F[i] = false;
		}
        Trace[0] = 0;
        D[0] = 0;
        F[0] = true;
        while (!F[n-1]) {
        	long min = Integer.MAX_VALUE;
        	int u = -1;
        	for (int i = 0; i < n; i++) {
				if (!F[i] && D[i] < min) {
					min = D[i];
					u = i;
				}
			}
        	F[u] = true;
        	if (!F[n-1]) {
        		for (int i = 0; i < n; i++) {
					if (!F[i] && D[i] > D[u] + A[u][i]) {
						D[i] = D[u] + A[u][i];
						Trace[i] = u;
					}
				}
        	}
        }
        System.out.println(D[n-1]);
//        int i = Trace[n-1];
//        while (i != 0) {
//        	System.out.print((i + 1) + "<-");
//        	i = Trace[i];
//        }        
//        System.out.print(n);
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
