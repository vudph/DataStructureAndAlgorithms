package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in);
		int n = StandardReader.nextInt();
		int A[] = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = StandardReader.nextInt();
		}
		int q = StandardReader.nextInt();
		int l[] = new int[q];
		int r[] = new int[q];
		int x[] = new int[q];
		int y[] = new int[q];
		int maxY = 2;
		for (int i = 0; i < q; i++) {
			l[i] = StandardReader.nextInt();
			r[i] = StandardReader.nextInt();
			x[i] = StandardReader.nextInt();
			y[i] = StandardReader.nextInt();
			if (y[i] > maxY) {
				maxY = y[i];
			}
		}
		boolean prime[] = sieveOfEratosthenes(maxY);
//		int numPrimeX2Y = 0;
//		for (int i = minX; i <= maxY; i++) {
//			if(prime[i]) 
//				numPrimeX2Y++;
//		}
		
		for (int i = 0; i < q; i++) {
			System.out.println(F(l[i], r[i], x[i], y[i], A, prime));
		}
	}
	
	private static int F(int l, int r, int x, int y, int A[], boolean prime[]) {
		int res = 0;
		for (int i = x; i <= y; i++) {
			if (prime[i]) {
				for (int j = l-1; j < r; j++) {
					int num = A[j];
					int exp = 0;
					while (num % i == 0) {
						exp++;
						num = num / i;
					}
					res += exp;
				}
			}
//			System.out.println(res);
		}
		return res;
	}
	
	private static boolean[] sieveOfEratosthenes(int n) {
		// initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider multiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        return isPrime;
        // count primes from x -> y
//        int x = 0, y = 100;
//        int primes = 0;
//        for (int i = x; i <= y; i++) {
//            if (isPrime[i]) {
//            	System.out.println(i);
//            	primes++;
//            }
//        }
//        System.out.println("The number of primes <= " + n + " is " + primes);
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
