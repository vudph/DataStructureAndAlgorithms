package com.algorithms.codility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SolutionIter implements Iterable<Integer> {
	private List<Integer> inputLines;
	
	public SolutionIter(Reader inp) throws IOException {
		inputLines = readLines(inp);
			
	}
	
	private List<Integer> readLines(Reader fileReader) throws IOException {
	    List<Integer> lines = new ArrayList<>();
	    boolean newLine = false;
	    int c, p = 0;
	    StringBuilder line = new StringBuilder();
	    while(-1 != (c = fileReader.read())) {
	        if(c == '\n' && p != '\r') {
	            newLine = true;
	        } else if(c == '\r') {
	            newLine = true;
	        } else {
	            if(c != '\n' && c != '\r') {
	                line.append((char) c);  
	            }
	        }
	        if(newLine) {
	        	if (isInteger(line.toString().trim())) {
	        		Integer n = Integer.parseInt(line.toString().trim());
	        		if (n <= 1000000000 && n >= -1000000000) {
	        			lines.add(n);
	        		}
	        	}
	            line = new StringBuilder();
	            newLine = false;
	        }
	        p = c;
	    }
	    if(line.length() > 0) {
	    	if (isInteger(line.toString().trim())) {
	    		Integer n = Integer.parseInt(line.toString().trim());
        		if (n <= 1000000000 && n >= -1000000000) {
        			lines.add(n);
        		}
        	}
	    }
	    return lines;
	}
	
	private boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	private boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}	
	
	@Override
	public Iterator<Integer> iterator() {
		return inputLines.iterator();
	}

	public static void main(String[] args) throws IOException {
			Reader fr = new FileReader("C:\\test.txt");
			
			for (Integer x : new SolutionIter(fr)) {
				System.out.println(x);
			}
			
//			SolutionIter sol = new SolutionIter(fr);
//			Iterator<Integer> iter = sol.iterator();
//			while(iter.hasNext()) {
//				try {
//					int ret = iter.next();
//					if (ret <= 1000000000 && ret >= -1000000000)
//						System.out.println(ret);
//				} catch (NumberFormatException ex) {
//					((IntegerIterator)iter).increasePosition();
//				}
//			}
			fr.close();
	}
	
	static class IntegerIterator implements Iterator<Integer> {
		List<String> input;
		int position = 0;
		
		public IntegerIterator(List<String> inp) {
			input = inp;
		}

		@Override
		public boolean hasNext() {
			if(position >= input.size() || input.get(position) == null)
				return false;
			return true;
		}

		@Override
		public Integer next() {
			Integer res = Integer.parseInt(input.get(position));
			position++;
			return res;
		}
		
			
	}
}
