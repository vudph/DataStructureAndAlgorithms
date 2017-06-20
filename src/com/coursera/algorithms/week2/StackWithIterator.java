package com.coursera.algorithms.week2;

import java.util.Iterator;

public class StackWithIterator<T> implements Iterable<T> {
	private Node<T> first;
	
	private static class Node<T> {
		T item;
		Node<T> next;
	}
	
	private class ListIterator implements Iterator<T> {
		Node<T> current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}
	}
	
	private void push(T item) {
		Node<T> oldFirst = first;
		first = new Node<T>();
		first.item = item;
		first.next = oldFirst;
	}
	
	private T pop() {
		T item = first.item;
		first = first.next;
		return item;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	public static void main(String[] args) {
		String st = "1 2 3 4 5";
		String in[] = st.split(" ");
		StackWithIterator<String> stackOfString = new StackWithIterator<>();
		for (String s : in) {
			if (s.equals("-")) {
				stackOfString.pop();
			} else {
				stackOfString.push(s);
			}
		}
		for (String s : stackOfString) {
			System.out.print(s + " ");
		}
		System.out.println("\nUsual way without Iterator:");
		Node current = stackOfString.first;
		while(current.next != null) {
			System.out.print(current.item + " ");
			current = current.next;
		}
		System.out.print(current.item + " ");
	}


}
