package com.algorithms;
/*
{3,3,1,1,2}, 
{3,3,3,1,2}, 
{3,2,2,2,6}, 
{2,2,6,6,6}
 */
public class AreaSquare {

	int dx[] = {-1, 0, 1, 0};
	int dy[] = {0, -1, 0, 1};
	Integer [][]lt = new Integer[50][50];
	Integer [][]arr = { {3,3,1,1,2}, {3,3,3,1,2}, {3,2,2,2,6}, {2,2,6,6,6}};
	int numOfArea = 0;
	int n, m;
	
	public boolean outOfArray(int row, int col){
		if ( row > -1 &&  row < m && col > -1 && col < n)
			return true;
		return false;
	}
	
	public void spread (int r, int c){
		int row, col;
		lt[r][c] = numOfArea;
		System.out.print("(" + r + ", " + c + ") ");
		for(int k=0; k<4; k++){
			row = r + dx[k];
			col = c + dy[k];
			if (outOfArray(row, col) && arr[r][c] == arr[row][col] && lt[row][col] == 0){
				spread(row, col);
			}
		}
	}
	
	public static void main(String[] args) {		
		AreaSquare as = new AreaSquare();
		as.m = 4;
		as.n = 5;		
		for (int i=0; i<50; i++)
			for (int j=0; j<50; j++)
				as.lt[i][j] = 0;
		for(int i=0; i<as.m; i++)
			for(int j=0; j<as.n; j++)
				if(as.lt[i][j] == 0){
					as.numOfArea++;
					as.spread(i, j);
					System.out.println();
				}
		System.out.println();
		for(int i=0; i<as.m; i++){
			for(int j=0; j<as.n; j++)
				System.out.print(as.lt[i][j]);
			System.out.println();			
		}
	}

}
