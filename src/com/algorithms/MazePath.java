package com.algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazePath {

	public static void main(String[] args) {
		int n = 4;
		int m = 5;
		int A[][] = {
				{0, 4, 0 , 0 , 0 },
				{8, 3, 14, 10, 12},
				{0, 0, 1 , 6 , 9 },
				{0, 0, 6 , 9 , 0 }
		};
		Point start = new Point(A[1][1], 1, 1);
		Point p = findShortestPathToExit(A, n, m, start);
		if (p != null) {
			while (p != null) {
				System.out.print(p.toString() + "<-");
				p = p.prev;
			}
		} else {
			System.out.println("Not found");
		}
	}

	private static Point findShortestPathToExit(int A[][], int n, int m, Point start) {
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);
		}
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		while(!queue.isEmpty()) {
			Point p = queue.remove();
			visited[p.x][p.y] = true;
			if (isExitGate(n, m, p)) {
				return p;
			}
			for (int k = 0; k < 4; k++) {
				int i = p.x + dx[k];
				int j = p.y + dy[k];
				if (isInMaze(n, m, i, j)) {
					if (!visited[i][j] && canMove(A[p.x][p.y], k)) {
						Point next = new Point(A[i][j], i, j);
						next.prev = p;
						queue.add(next);
					}
				}
			}
		}
		return null;
	}
	
	private static boolean canMove(int value, int k) {
		if ((value & (int)Math.pow(2, k)) > 0) {
			return true;
		}
		return false;
	}
	
	private static boolean isInMaze(int n, int m, int i, int j) {
		if ((i >= 0 && i < n) && (j >= 0 && j < m)) {
			return true;
		}
		return false;
	}
	
	private static boolean isExitGate(int n, int m, Point p) {
		if (p.x == 0 && (p.value & 1) > 0) {
			return true;
		} else if (p.x == n -1 && (p.value & 4) > 0) {
			return true;
		} else if (p.y == 0 && (p.value & 8) > 0) {
			return true;
		} else if (p.y == m - 1 && (p.value & 2) > 0) {
			return true;
		}
		return false;
	}
	
	static class Point {
		int x;
		int y;
		int value;
		Point prev;
		public Point(int value, int i, int j) {
			this.value = value;
			this.x = i;
			this.y = j;
		}
		
		public String toString() {
			return "[" + value + "(" + x + ", " + y + ")" + "]";
		}
	}
}
