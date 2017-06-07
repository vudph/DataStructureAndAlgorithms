package com.algorithms.codechef.practices;

import java.util.Scanner;

//https://www.codechef.com/problems/COINS

public class Main {

//	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		while (s.hasNextInt()) {
//            int n = s.nextInt();
//            System.out.println(solve(n));
//        }
//		System.exit(0);
//	}
//	
//	private static int solve(int n) {
//		if (n < 4)
//			return n;
//		int div2 = n / 2;
//        int div3 = n / 3;
//        int div4 = n / 4;
//        int sum = solve(div2) + solve(div3) + solve(div4);
//        if (sum > n) {
//        	return sum;
//        } 
//        return n;
//	}
	
	
	void initiate(int[] dp){
        int i;
        long sum;
        for (i = 0; i <1001; i++) {
 
            sum=dp[i/2]+dp[i/3]+dp[i/4];
            if(sum>=i)
                dp[i]=(int) sum;
            else
                dp[i]=i;
        }
}
 
    long starT(long num,int[] dp){
        if(num<=1000)
            return dp[(int)num];
        else
           return starT(num/2,dp)+starT(num/3,dp)+starT(num/4,dp);
    }
 
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        Main x=new Main();
        ////starts here
 
        long num;
        int[] dp = new int[1001];
 
        x.initiate(dp);
 
        while (sc.hasNext()) {
            num = sc.nextLong();
           System.out.println(x.starT(num,dp));
           // System.exit(1);
        }
        System.exit(0);
        //ends here
    }

}
