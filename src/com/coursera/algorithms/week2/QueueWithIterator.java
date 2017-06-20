package com.coursera.algorithms.week2;

import java.util.Iterator;

public class QueueWithIterator<T> implements Iterable<T> {
	private Node<T> first;
	private Node<T> last;
	
	private static class Node<T> {
		T item;
		Node<T> next;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
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
	
	private boolean isEmpty() {
		return first == null;
	}
	
	private void enqueue(T item) {
		Node<T> oldLast = last;
		last = new Node<T>();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
	}
	
	private T dequeue() {
		T item = first.item;
		first = first.next;
		if (first == null) {
			last = null;
		}
		return item;
	}

	public static void main(String[] args) {
		int input[] = {1, 2, 3, 4, 5};
		QueueWithIterator<Integer> queue = new QueueWithIterator<>();
		for (int i = 0; i < input.length; i++) {
			queue.enqueue(input[i]);
		}
		System.out.println("Usual way without Iterator:");
		Node<Integer> current = queue.first;
		while(current.next != null) {
			System.out.print(current.item + " ");
			current = current.next;
		}
		System.out.println(current.item + " ");
		System.out.println("Using iterator");
		for (Integer integer : queue) {
			System.out.print(integer + " ");
		}
	}

}
