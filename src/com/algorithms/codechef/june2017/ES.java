package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ES {

	private static final double e = 2.7182818284;
	
	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in); // connect Reader to an input stream
        String n = StandardReader.next();
        BigDecimal N = new BigDecimal(n);
        BigInteger sum = new BigInteger("0");
        BigDecimal i;
        for(i = new BigDecimal("1"); i.compareTo(N) <= 0; i = i.add(BigDecimal.ONE)) {
        	BigInteger b = etfBig(i);
//        	System.out.println(b);
        	sum = sum.add(b);
        }
             
		System.out.println(sum);
	}
	
	private static BigInteger etfBig(BigDecimal n) {
		BigDecimal bi1, bi2;

	    bi1 = new BigDecimal("2.7182818284590452353602874713527");
	    bi2 = bi1.multiply(n);
	    String ret = bi2.toString();
		return new BigInteger(ret.substring(0, ret.indexOf(".")));
//	    BigInteger result = n;
//	    BigInteger i;
//
//	    for(i = new BigInteger("2"); (i.multiply(i)).compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
//	         if((n.mod(i)).compareTo(BigInteger.ZERO) == 0) 
//	         result = result.divide(i);
//	         while(n.mod(i).compareTo(BigInteger.ZERO)== 0 ) 
//	             n = n.divide(i);
//	     }      
//	 if(n.compareTo(BigInteger.ONE) > 0)
//	 result = result.subtract((result.divide(n)));
//	 return result;
	}

	private static String multiply(String num1, String num2){//24691366.8676768849594836
        int product, carry=0, sum=0;
        String result = new String("");
        String partial = new String("");
        ArrayList<String> partialList = new ArrayList<String>();

        /* computing partial products using this loop. */
        for(int j=num2.length()-1 ; j>=0 ; j--) {
            for(int i=num1.length()-1 ; i>=0 ; i--) {       

                product = Integer.parseInt((new Character(num1.charAt(i))).toString()) * 
                                Integer.parseInt((new Character(num2.charAt(j))).toString()) + carry;               
                carry = product/10;
                partial = Integer.toString(product%10) + partial;               
            }       

            if(carry != 0)
                partial = Integer.toString(carry) + partial;

            partialList.add(partial);
            partial = "";
            carry = 0;
        }                           

        /* appending zeroes incrementally */
        for(int i=0 ; i<partialList.size() ; i++)
            partialList.set(i, partialList.get(i) + (Long.toString( (long)java.lang.Math.pow(10.0,(double)i))).substring(1)   );        

        /* getting the size of the largest partial product(last) */
        int largestPartial = partialList.get(partialList.size()-1).length();

        /* prefixing zeroes */
        int zeroes;
        for(int i=0 ; i<partialList.size() ; i++) {
            zeroes =  largestPartial - partialList.get(i).length();

            if(zeroes >= 1)
            partialList.set(i, (Long.toString( (long)java.lang.Math.pow(10.0,(double)zeroes))).substring(1) + partialList.get(i)   );
        }

        /* to compute the result */
        carry = 0;
        for(int i=largestPartial-1 ; i>=0 ; i--) {

            sum = 0;
            for(int j=0 ; j<partialList.size() ; j++)
                sum = sum + Integer.parseInt(new Character(partialList.get(j).charAt(i)).toString());

            sum = sum + carry;
            carry = sum/10;         
            result = Integer.toString(sum%10) + result;     
        }

        if(carry != 0)
            result = Integer.toString(carry) + result;

        return result;
    }
	
	static class StandardReader {
		static BufferedReader reader;
	    static StringTokenizer tokenizer;
 
	    /** call this method to initialize reader for InputStream */
	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                     new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }
 
	    /** get next word */
	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            //TODO add check for eof if necessary
	            tokenizer = new StringTokenizer(
	                   reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }
 
	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
	    
	    static long nextLong() throws IOException {
	        return Long.parseLong( next() );
	    }
		
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }
	}
}
