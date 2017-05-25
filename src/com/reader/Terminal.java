package com.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Terminal {

	public static void main(String[] args) throws IOException {
//		Read in terminal
		
//		Scanner in = new Scanner(System.in); // slow
//        int T = in.nextInt();
//        for(int t = 0; t < T; t++) {
//            long N = in.nextLong();
//            long K = in.nextLong();
//            long MOD = in.nextInt();
//            
//            System.out.println("N: " + N + ", K: " + K + ", MOD: " + MOD);
//        }
//        in.close();
        
		//Better/quicker approach
//        StandardReader.init(System.in ); // connect Reader to an input stream		
		File file = new File("C:/robots.txt");
		FileInputStream fis = null;
		fis = new FileInputStream(file); //read from file
		StandardReader.init(fis);
        int n = StandardReader.nextInt();
        for (int i = 0; i < n; i++) {
			long x = StandardReader.nextInt();
			long y = StandardReader.nextInt();
			long z = StandardReader.nextInt();
			System.out.println("x: " + x + ", y: " + y + ", z: " + z);
		}
        fis.close();
	}

}
