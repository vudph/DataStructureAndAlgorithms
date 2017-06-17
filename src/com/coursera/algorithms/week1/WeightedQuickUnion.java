package com.coursera.algorithms.week1;

public class WeightedQuickUnion extends AbstractUnionFind {
	private int[] sz;
	
	public WeightedQuickUnion(int n) {
		super(n);
		sz = new int[n];
		for (int i = 0; i < n; i++) 
			sz[i] = 1;
	}

	@Override
	public int find(int p) {
		while (p != id[p]) 
			p = id[p];
		return p;
	}

	@Override
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) 
			return;
		// Make smaller root point to larger one.
		if (sz[i] < sz[j]) { 
			id[i] = j; 
			sz[j] += sz[i]; 
		} else { 
			id[j] = i; 
			sz[i] += sz[j]; 
		}
		count--;
	}

	public static void main(String[] args) {
		int n = 10;
		AbstractUnionFind qf = new WeightedQuickUnion(n);
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
