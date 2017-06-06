package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
//https://www.codechef.com/JUNE17/problems/CLONEME

public class Main {
 
	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in);
		int T = StandardReader.nextInt();
        while(T-- > 0) {
        	int n = StandardReader.nextInt();
        	int q = StandardReader.nextInt();
        	int A[] = new int[n];
        	for (int i = 0; i < n; i++) {
				A[i] = StandardReader.nextInt();
			}
        	for (int i = 0; i < q; i++) {
				int a = StandardReader.nextInt();
				int b = StandardReader.nextInt();
				int c = StandardReader.nextInt();
				int d = StandardReader.nextInt();
//				if (a > n || b > n || c > n || d > n || a > b || c > d || (d - c != b - a)) {
//					System.out.println("NO");
//					continue;
//				}
				a = a - 1;
				c = c - 1;
				CustomArrayList list1 = new CustomArrayList();
				for (int j = a; j < b; j++) {
					list1.insert(A[j]);
				}
				CustomArrayList list2 = new CustomArrayList();
				for (int j = c; j < d; j++) {
					list2.insert(A[j]);
				}
//				if (list1.size() != list2.size()) {
//					System.out.println("NO");
//					continue;
//				}
				int count = 0;
				for (int j = 0; j < list1.size(); j++) {
					if(list1.get(j) != list2.get(j)) {
						count++;
					}
					if (count > 1) {
						break;
					}
				}
				
//				SortedSet<Integer> sub1 = new TreeSet<>();
//				for (int j = a; j < b; j++) {
//					sub1.add(A[j]);
//				}
//				SortedSet<Integer> sub2 = new TreeSet<>();
//				for (int j = c; j < d; j++) {
//					sub2.add(A[j]);
//				}
//
//				Iterator<Integer> itr1 = sub1.iterator();
//				Iterator<Integer> itr2 = sub2.iterator();
//				int count = 0;
//			    while(itr1.hasNext()) {
//			    	if (itr1.next() != itr2.next()) {
//			    		count++;
//			    	}
//			    	if (count > 1) {
//			    		break;
//			    	}
//			    }
				if (count < 2) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
        }
	}
	
	static class CustomArrayList extends ArrayList<Integer> {
		private static final long serialVersionUID = 9083379952225638314L;
		public CustomArrayList() {
			super();
		}
		
		public void insert(int x) {
			// loop through all elements
		    for (int i = 0; i < size(); i++) {
		        // if the element you are looking at is smaller than x, 
		        // go to the next element
		        if (get(i) < x) continue;
		        // if the element equals x, return, because we don't add duplicates
		        // if (get(i) == x) return;
		        // otherwise, we have found the location to add x
		        add(i, x);
		        return;
		    }
		    // we looked through all of the elements, and they were all
		    // smaller than x, so we add ax to the end of the list
		    add(x);
		}
		
		public String toString(){
	        if(super.size() == 0) 
	        	return "[]";
	        String s = "[" + super.get(0).toString();
	        for(int i = 1; i < super.size(); i++){
	            s += ", " + super.get(i).toString();
	        }
	        return s += "]";
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