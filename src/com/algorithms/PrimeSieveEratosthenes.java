package com.algorithms;

import java.util.Arrays;


/******************************************************************************
 *  Compilation:  javac PrimeSieve.java
 *  Execution:    java -Xmx1100m PrimeSieve n
 *  
 *  Computes the number of primes less than or equal to n using
 *  the Sieve of Eratosthenes.
 *
 *  % java PrimeSieve 25
 *  The number of primes <= 25 is 9
 *
 *  % java PrimeSieve 100
 *  The number of primes <= 100 is 25
 *
 *  % java -Xmx100m PrimeSieve 100000000
 *  The number of primes <= 100000000 is 5761455
 *
 *  % java PrimeSieve -Xmx1100m 1000000000 
 *  The number of primes <= 1000000000 is 50847534
 * 
 *
 *  The 110MB and 1100MB is the amount of memory you want to allocate
 *  to the program. If your computer has less, make this number smaller,
 *  but it may prevent you from solving the problem for very large
 *  values of n.
 *
 *
 *                  n     Primes <= n
 *  ---------------------------------
 *                 10               4   
 *                100              25  
 *              1,000             168  
 *             10,000           1,229  
 *            100,000           9,592  
 *          1,000,000          78,498  
 *         10,000,000         664,579  
 *        100,000,000       5,761,455  
 *      1,000,000,000      50,847,534  
 *
 ******************************************************************************/
//https://en.wikipedia.org/wiki/File:Sieve_of_Eratosthenes_animation.gif

public class PrimeSieveEratosthenes {

	public static void main(String[] args) {
		int n = 100;
//		sieveOfEratosthenes(n);
		sieveOfEratosthenes(50, 100); 
	}
	
	private static void sieveOfEratosthenes(int n) {
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

        // count primes from x -> y
        int x = 0, y = 100;
        int primes = 0;
        for (int i = x; i <= y; i++) {
            if (isPrime[i]) {
            	System.out.println(i);
            	primes++;
            }
        }
        System.out.println("The number of primes <= " + n + " is " + primes);
	}

	private static void sieveOfEratosthenes(int x, int y) {
		// initially assume all integers are prime
        boolean[] isPrime = new boolean[(y-x)+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = x; factor*factor <= y; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider multiples factor, factor+1, ...,  n/factor
            if (isPrime[factor-x]) {
                for (int j = factor; factor*j <= y; j++) {
                    isPrime[factor*j-x] = false;
                }
            }
        }

        // count primes from x -> y
        int primes = 0;
        for (int i = x; i <= y; i++) {
            if (isPrime[i-x]) {
            	System.out.println(i);
            	primes++;
            }
        }
        System.out.println("The number of primes <= " + y + " is " + primes);
	}
}
