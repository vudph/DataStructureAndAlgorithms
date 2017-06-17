package com.coursera.algorithms.week1;

public abstract class AbstractUnionFind {
	protected int id[]; 
	protected int count; // number of components
	
	public AbstractUnionFind(int n) {
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public abstract int find(int p);
	public abstract void union(int p, int q);
}
