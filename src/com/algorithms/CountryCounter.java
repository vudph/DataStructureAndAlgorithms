package com.algorithms;
/*
A rectangular map consisting of N rows and M columns of square areas is given. Each area is painted with a certain color.
Two areas on the map belong to the same country if the following conditions are met:
•	they have the same color;
•	it is possible to travel from one area to the other by moving only north, south, west or east without moving over areas of a different color.
The map can be described by a zero-indexed matrix consisting of N rows and M columns of integers. The color of each area is described by the corresponding element of the matrix. Two areas have the same color if and only if their corresponding matrix elements have the same value.
For example, consider the following matrix A consisting of seven rows and three columns:
A[0][0] = 5    A[0][1] = 4    A[0][2] = 4
A[1][0] = 4    A[1][1] = 3    A[1][2] = 4
A[2][0] = 3    A[2][1] = 2    A[2][2] = 4
A[3][0] = 2    A[3][1] = 2    A[3][2] = 2
A[4][0] = 3    A[4][1] = 3    A[4][2] = 4
A[5][0] = 1    A[5][1] = 4    A[5][2] = 4
A[6][0] = 4    A[6][1] = 1    A[6][2] = 1
Matrix A describes a map that is colored with five colors. Areas on the map belong to eleven different countries:
•	area A[0][0] forms a one-area country;
•	areas A[0][1], A[0][2], A[1][2], A[2][2] belong to the same country;
•	area A[1][0] forms a one-area country;
•	area A[1][1] forms a one-area country;
•	area A[2][0] forms a one-area country;
•	areas A[2][1], A[3][0], A[3][1], A[3][2] belong to the same country;
•	areas A[4][0], A[4][1] belong to the same country;
•	areas A[4][2], A[5][1], A[5][2] belong to the same country;
•	area A[5][0] forms a one-area country;
•	area A[6][0] forms a one-area country;
•	areas A[6][1], A[6][2] belong to the same country.
Write a function
class Solution { public int countries_count(int[][] A); } 
that, given a zero-indexed matrix A consisting of N rows and M columns of integers, returns the number of different countries that the areas of the map described by matrix A belong to.
Assume that:
•	N is an integer within the range [1..1,000,000];
•	M is an integer within the range [1..1,000,000];
•	the number of elements in matrix A is within the range [1..1,000,000];
•	each element of matrix A is an integer within the range [−1,000,000,000..1,000,000,000].
For example, given matrix A consisting of seven rows and three columns corresponding to the example above, the function should return 11.
Complexity:
•	expected worst-case time complexity is O(N*M);
•	expected worst-case space complexity is O(N*M).

*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CountryCounter {

	public static void main(String[] args) {
		int A[][] = { 
				{5, 4, 4},
				{4, 3, 4},
				{3, 2, 4},
				{2, 2, 2},
				{3, 3, 4},
				{1, 4, 4},
				{4, 1, 1}
		};
		System.out.println(countriesCount(A));
	}
	
	private static int countriesCount(int A[][]) {
		int N = A.length;
		int M = A[0].length;
		boolean visited[][] = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					count++;
//					dfsUsingStack(A, visited, N, M, i, j);
					bfs(A, visited, N, M, i, j);
				}
			} 
		}
		return count;
	}

	private static void dfsUsingStack(int A[][], boolean visited[][], int N, int M, int i, int j) {
		Stack<Point> stack = new Stack<>();		
		stack.push(new Point(i, j));
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		while (!stack.isEmpty()) {
			Point point = (Point) stack.pop();
			visited[point.x][point.y] = true;
			for (int k = 0; k < 4; k++) {
				int x = point.x + dx[k];
				int y = point.y + dy[k];
				if (x >= 0 && x < N && y >= 0 && y < M && !visited[x][y]) {
					if(A[point.x][point.y] == A[x][y]) {						
						stack.push(new Point(x, y));
					}
				}
			}
		}
	}
	
	private static void bfs(int A[][], boolean visited[][], int N, int M, int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		int dx[] = {-1, 0, 1, 0};
		int dy[] = {0, 1, 0, -1};
		while (!queue.isEmpty()) {
			Point point = queue.remove();
			visited[point.x][point.y] = true;
			for (int k = 0; k < 4; k++) {
				int x = point.x + dx[k];
				int y = point.y + dy[k];
				if (x >= 0 && x < N && y >= 0 && y < M && !visited[x][y]) {
					if(A[point.x][point.y] == A[x][y]) {						
						queue.add(new Point(x, y));
					}
				}
			}
		}
	}
	
	static class Point {
		int x;
		int y;
		public Point(int i, int j) {
			x = i;
			y = j;
		}
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
}
