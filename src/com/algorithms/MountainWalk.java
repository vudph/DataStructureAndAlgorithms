package com.algorithms;

import java.util.Arrays;

/*
1) Problem Statement
You are in a mountainous area which is represented by a String[] areaMap. The j-th character of the i-th element of the areaMap is a digit '0'-'9' representing the height of cell (i, j). You perform a walk in the area according to the following rules:
•	You start from cell (0, 0).
•	If you are in cell (i, j), you examine cells (i+1, j), (i, j-1), (i-1, j), (i, j+1) in this order. You go to the first of these cells you can enter. You can enter a cell if it is still on the map, you haven't been to it before and the difference between the heights of your current cell and the cell you want to enter is no bigger (in absolute value) than heightDifference.
•	You end your walk if you can not make another move, i.e., if you can not enter any neighboring cell.
You must return the number of cells that you visit while performing your walk. You visit cell (i, j) if and only if you enter cell (i, j) at some point during your walk (the starting cell (0, 0) also counts as entered, i.e., you definitely visit (0, 0)). Note that you will visit each cell at most once since you never enter the same cell twice.
 
2) Definition
Class:	MountainWalk
Method:	cellsVisited
Parameters:	String[], int
Returns:	int
Method signature:	int cellsVisited(String[] areaMap, int heightDifference)
(be sure your method is public)


3) Constraints
- areaMap will contain between 1 and 50 elements, inclusive.
- All the elements of areaMap will contain the same number of characters.
- Each element of areaMap will contain between 1 and 50 digits ('0' - '9'), inclusive.
- heightDifference will be between 0 and 9, inclusive.

Examples
 
0)	
{"056",
 "135",
 "234"}
1

1)	
{"056",
 "195",
 "234"}
1

2)	
{"865",
 "123",
 "111"}
3
3)	
{"00009876543210",
 "00009876543210",
 "00009876543210",
 "00009876543210"}
8

4)	
{"0000",
 "0000",
 "0000",
 "0000",
 "9999",
 "8888",
 "7777",
 "6666",
 "5555",
 "4444",
 "3333",
 "2222",
 "1111",
 "0000"}
3

5)	
{"173642855131893831828253420",
 "126290035950506994475683704",
 "381277675415026563959463393",
 "019782700912864681764582260",
 "496448425114634806770407597",
 "049628433145840178727435051",
 "117194708226266248973780562",
 "398138380998246682323622510",
 "408178777661559971959512111"}
8
	
*/

public class MountainWalk {

	public static void main(String[] args) {
		String areaMap[] = {"865", "123", "111"};
		
		int heightDifference = 9;
		System.out.println(cellsVisited1(areaMap, heightDifference));
	}

	private static int cellsVisited(String[] areaMap, int heightDifference) {
		int di[] = {1, 0, -1, 0};
		int dj[] = {0, -1, 0, 1};
		int noRow = areaMap.length;
		int noColumn = areaMap[0].length();
		boolean visited[][] = new boolean[noRow][noColumn];
		for (int i = 0; i < noRow; i++) {
			Arrays.fill(visited[i], false);
		}
		int i = 0, j = 0;
		System.out.print("(" + i + "," + j + ")");
		int currentValue = Character.getNumericValue(areaMap[i].charAt(j));
		visited[i][j] = true;
		int count = 1;
		int curri = i;
		int currj = j;
		for (int k = 0; k < 4; k++) {
			i = curri + di[k];
			j = currj + dj[k];
			
			if ((i >= 0 && i < noRow) && (j >= 0 && j < noColumn)) { // in map
				int height = Character.getNumericValue(areaMap[i].charAt(j));
				if (!visited[i][j] && (Math.abs(height - currentValue) <= heightDifference)) {
					System.out.print("->(" + i + "," + j + ")");
					visited[i][j] = true;
					count++;
					currentValue = height;
					curri = i;
					currj = j;
					k = -1; //reset k
				}
			}
		}
		System.out.println();
		return count;
		
	}
	
	
	private static boolean compareHeight(char val1, char val2, int heightDifference){
		
		int tmp;
		tmp = val2-val1;
		if( tmp <0)
			tmp = -tmp;		
		if(tmp <= heightDifference){			
			return true;
		}					
		return false;
	}
	
	private static boolean inMap (int hh, int cc, int m, int n){
		if(hh>-1 && hh<m && cc>-1 && cc < n)
			return true;
		return false;
	}
	
	private static int cellsVisited1(String []areaMap, int heightDifference){		
		int []row = {1, 0, -1, 0};
		int []col = {0, -1, 0, 1};
		int m, n;
		int res = 1;
		int x=0, y=0;
		System.out.print("(" + x + "," + y + ")");
		m = areaMap.length;
		n = areaMap[0].length();						
		Boolean Trace[][] = new Boolean[m][n];		
				
		for(int i=0; i<m; i++)
			for(int j=0; j<n; j++)
				Trace[i][j] = true;
		Trace[x][y] = false;		
		
		for(int k=0; k<4; k++){
			int dx = x + row[k];
			int dy = y + col[k];
			if(inMap(dx, dy, m, n)){
				if(Trace[dx][dy]){					
					if(compareHeight(areaMap[x].charAt(y), areaMap[dx].charAt(dy), heightDifference)){											
						Trace[dx][dy] = false;
						x = dx;
						y = dy;						
						res++;
						k = -1;
						System.out.print("->(" + x + "," + y + ")");
					}				
				}
			}
		}				
		return res;
	}
}
