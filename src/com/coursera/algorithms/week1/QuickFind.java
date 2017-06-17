package com.coursera.algorithms.week1;

import java.util.Iterator;
import java.util.Scanner;

public class QuickFind extends AbstractUnionFind {

	public QuickFind(int n) {
		super(n); //id[p] vs id[q]: p and q are connected iff they have the same id.
	}

	@Override
	public int find(int p) {
		return id[p];
	}

	@Override
	public void union(int p, int q) {
		// Put p and q into the same component.
		int pID = find(p);
		int qID = find(q);
		
		// Nothing to do if p and q are already in the same component.
		if (pID == qID) return;
		
		// Rename p’s component to q’s name.
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID) { 
				id[i] = qID;
			}
		}	
		count--;
	}

	public static void main(String[] args) {
		int n = 10;
		AbstractUnionFind qf = new QuickFind(n);
		String input[] = {"4 3", "3 8", "6 5", "9 4", "2 1", "5 0", "7 2", "6 1"};
		for (String st : input) {
			String split[] = st.split(" ");
			int p = Integer.parseInt(split[0]);
			int q = Integer.parseInt(split[1]);
			if(qf.connected(p, q))
				continue;
			qf.union(p, q);
		}
		/* 
		 1) 0-5-6-1-2-7 
		 2) 3-4-9
		    |
		    8
		*/
		System.out.println(qf.count + " components");
	}

}
