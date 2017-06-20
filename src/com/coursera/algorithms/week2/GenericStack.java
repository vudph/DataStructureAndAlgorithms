package com.coursera.algorithms.week2;

public class GenericStack<T> {
	private Node<T> first;
	
	private static class Node<T> {
		T item;
		Node<T> next;
	}

	private void push(T item) {
		Node<T> oldFirst = first;
		first = new Node<>();
		first.item = item;
		first.next = oldFirst;
	}

	private T pop() {
		T item = first.item;
		first = first.next;
		return item;
	}

	private boolean isEmpty() {
		return first == null;
	}

	public static void main(String[] args) {
		String st = "to be or not to - be - - that - - - is";
		String in[] = st.split(" ");
		GenericStack<String> stackOfString = new GenericStack<>();
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(stackOfString.pop() + " ");
			} else {
				stackOfString.push(s);
			}
		}
		System.out.println();
		String sn = "1 2 3 4 - 5 6 - - -";
		in = sn.split(" ");
		GenericStack<Integer> stackOfInt = new GenericStack<>();
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(stackOfInt.pop() + " ");
			} else {
				stackOfInt.push(Integer.parseInt(s));
			}
		}
	}

}