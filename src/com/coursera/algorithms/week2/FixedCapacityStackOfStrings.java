package com.coursera.algorithms.week2;

public class FixedCapacityStackOfStrings {
	private String[] stack;
	private int n;
	
	public FixedCapacityStackOfStrings(int capacity) {
		stack = new String[capacity];
		n = 0;
	}
	
	private void push(String item) {
		stack[n++] = item;
	}
	
	private String pop() {
		String item = stack[--n];
		stack[n] = null;
		return item;
	}
	

	public static void main(String[] args) {
		String st = "to be or not to - be - - that - - - is";
		String in[] = st.split(" ");
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(stack.pop() + " ");
			} else {
				stack.push(s);
			}
		}
	}

}
