package com.coursera.algorithms.week2;

public class StackOfStrings {
	private Node first;
	
	private void push(String item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}

	private String pop() {
		String item = first.item;
		first = first.next;
		return item;
	}

	private boolean isEmpty() {
		return first == null;
	}

	public static void main(String[] args) {
		String st = "to be or not to - be - - that - - - is";
		String in[] = st.split(" ");
		StackOfStrings stack = new StackOfStrings();
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(stack.pop() + " ");
			} else {
				stack.push(s);
			}
		}
	}

	private class Node {
		String item;
		Node next;
	}
}
