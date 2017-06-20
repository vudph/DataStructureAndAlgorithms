package com.coursera.algorithms.week2;

public class ResizingArrayStackOfStrings {
	private String[] stack = new String[1];
	private int n = 0;
	
	private void push(String item) {
		if (n == stack.length) {
			resize(2 * stack.length);
		}
		stack[n++] = item;
	}
	
	private String pop() {
		String item = stack[--n];
		stack[n] = null;
		if (n > 0 && n == stack.length/4) {
			resize(stack.length/2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		String copy[] = new String[capacity];
		for (int i = 0; i < n; i++) {
			copy[i] = stack[i];
		}
		stack = copy;
	}
	

	public static void main(String[] args) {
		String st = "to be or not to - be - - that - - - is";
		String in[] = st.split(" ");
		ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(stack.pop() + " ");
			} else {
				stack.push(s);
			}
		}
	}

}
