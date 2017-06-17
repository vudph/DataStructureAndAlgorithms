package com.coursera.algorithms.week1;

public class QuickUnion extends AbstractUnionFind {

	public QuickUnion(int n) {
		super(n); //id[i] is parent of i. Root of i is id[id[id[...id[i]...]]]
	}

	@Override
	public int find(int p) {
		while (p != id[p]) 
			p = id[p];
		return p;
	}

	@Override
	public void union(int p, int q) {
		// Give p and q the same root.
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) // the same component 
			return;
		id[pRoot] = qRoot;
		count--;
	}

	public static void main(String[] args) {
		int n = 10;
		AbstractUnionFind qu = new QuickUnion(n);
		String input[] = {"4 3", "3 8", "6 5", "9 4", "2 1", "5 0", "7 2", "6 1"};
		for (String st : input) {
			String split[] = st.split(" ");
			int p = Integer.parseInt(split[0]);
			int q = Integer.parseInt(split[1]);
			if(qu.connected(p, q))
				continue;
			qu.union(p, q);
		}
		/* 
		 1) 0-5-6-1-2-7 
		 2) 3-4-9
		    |
		    8
		*/
		System.out.println(qu.count + " components");
	}

}
