package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
//https://www.codechef.com/JUNE17/problems/SUMQ
 
public class Main {
 
	public static void main(String[] args) throws IOException {
//		for (int i = 0; i < 100; i++) {
//			System.out.print(1000000000 + " ");
//		}
		
		StandardReader.init(System.in); // connect Reader to an input stream		
        int T = StandardReader.nextInt();
        while(T-- > 0) {
        	int p = StandardReader.nextInt();
        	int q = StandardReader.nextInt();
        	int r = StandardReader.nextInt();
        	
			SortedSet<Long> A = new TreeSet<>();
			SortedSet<Long> B = new TreeSet<>();
			SortedSet<Long> C = new TreeSet<>();
			for (int i = 0; i < p; i++) {
				A.add(StandardReader.nextLong());
			}
			for (int i = 0; i < q; i++) {
				B.add(StandardReader.nextLong());
			}
			for (int i = 0; i < r; i++) {
				C.add(StandardReader.nextLong());
			}
			long sum = 0;
			
//			long maxY = B.last();
//			SamplePredicate<Long> filter = new SamplePredicate<>();
//			filter.varc1 = maxY;
//			A.removeIf(filter); //remove all items which are lager than maxY;
//			C.removeIf(filter);
			
			for (Iterator yIter = B.iterator(); yIter.hasNext();) {
				Long y = (Long) yIter.next();
				SortedSet<Long> subA = A.headSet(y + 1);
				SortedSet<Long> subC = C.headSet(y + 1);
				if (subA.isEmpty() || subC.isEmpty()) {
					continue;
				}
				
				for (Iterator xIter = subA.iterator(); xIter.hasNext();) {
					long x = (long) xIter.next();
					if (x <= y) {
						for (Iterator zIter = subC.iterator(); zIter
								.hasNext();) {
							long z = (long) zIter.next();
							if (z <= y) {
								long s = (x + y) * (y + z);
								sum = ((sum % 1000000007) + s) % 1000000007;
							}
						}
					}
				}
				
			}
        	
        	
//        	long A[] = new long[p];
//        	long B[] = new long[q];
//        	long C[] = new long[r];
//			for (int i = 0; i < p; i++) {
//				A[i] = StandardReader.nextLong();
//			}
//			for (int i = 0; i < q; i++) {
//				B[i] = StandardReader.nextLong();
//			}
//			for (int i = 0; i < r; i++) {
//				C[i] = StandardReader.nextLong();
//			}
//			long sum = 0;
//			for (int i = 0; i < q; i++) {
//				for (int j = 0; j < p; j++) {
//					if (A[j] <= B[i]) {
//						for (int k = 0; k < r; k++) {
//							if (C[k] <= B[i]) {
////								long s = (A[j] + B[i]) * (B[i] + C[k]);
////								System.out.println(s);
////								System.out.println(A[j] + "," + B[i] + "," + C[k] + "=" + "(" + A[j] + "+" + B[i] + ")*(" + B[i] + "+" +  C[k] + ")=" + ((A[j] + B[i]) * (B[i] + C[k])));
//								
////								long s = 1; 
////								s = (s * (A[j] + B[i])) % 1000000007;
////								s = (s * (B[i] + C[k])) % 1000000007;
////								sum += s;
//								
//								long s = (A[j] + B[i]) * (B[i] + C[k]);
//								sum = ((sum % 1000000007) + s) % 1000000007;
//							}
//						}
//					}
//				}
//			}
			System.out.println(sum);
        }
	}
 
//	static class SamplePredicate<Long> implements Predicate<Long>{  
//		Long varc1;  
//		public boolean test(Long varc){
//			long v0 = (long) varc1;
//			long v1 = (long) varc;
//			if(v1 > v0){  
//				return true;  
//			}  
//			return false;  
//		}
//	} 
	
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
