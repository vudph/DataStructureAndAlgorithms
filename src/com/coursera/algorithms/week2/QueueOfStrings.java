package com.coursera.algorithms.week2;

import java.util.List;

public class QueueOfStrings {
	private Node first;
	private Node last;
	
	private class Node {
		String item;
		Node next;		
	}
	
	private boolean isEmpty() {
		return first == null;
	}
	
	private void enqueue(String item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
	}
	
	private String dequeue() {
		String item = first.item;
		first = first.next;
		if (isEmpty()) {
			last = null;
		}
		return item;
	}

	public static void main(String[] args) {
		String st = "1 2 - 3 4 - 5 - - -";
		String in[] = st.split(" ");
		QueueOfStrings queue = new QueueOfStrings();
		for (String s : in) {
			if (s.equals("-")) {
				System.out.print(queue.dequeue() + " ");
			} else {
				queue.enqueue(s);
			}
		}

	}

}
