package com.algorithms.codechef.june2017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

//https://www.codechef.com/JUNE17/problems/CLONEME

public class CLONEME {
 
	public static void main(String[] args) throws IOException {
		StandardReader.init(System.in);
		int T = StandardReader.nextInt();
        while(T-- > 0) {
        	int n = StandardReader.nextInt();
        	int q = StandardReader.nextInt();
        	int A[] = new int[n];
        	for (int i = 0; i < n; i++) {
				A[i] = StandardReader.nextInt();
			}
        	for (int i = 0; i < q; i++) {
				int a = StandardReader.nextInt();
				int b = StandardReader.nextInt();
				int c = StandardReader.nextInt();
				int d = StandardReader.nextInt();
				if (a > n || b > n || c > n || d > n || a > b || c > d || (d - c != b - a)) {
					System.out.println("NO");
					continue;
				}
				a = a - 1;
				c = c - 1;
				
				SkipList<Integer> skipList1 = new SkipList<Integer>(new Comparator<Integer>() {
		            @Override
		            public int compare(Integer o1, Integer o2) {
		                return o1.compareTo(o2);
		            }
		        });
				for (int j = a; j < b; j++) {
					skipList1.add(A[j]);
				}
				
				SkipList<Integer> skipList2 = new SkipList<Integer>(new Comparator<Integer>() {
		            @Override
		            public int compare(Integer o1, Integer o2) {
		                return o1.compareTo(o2);
		            }
		        });
				for (int j = c; j < d; j++) {
					skipList2.add(A[j]);
				}
				
				Iterator<Integer> itr1 = skipList1.iterator();
				Iterator<Integer> itr2 = skipList2.iterator();
				int count = 0;
		        while(itr1.hasNext()) {
		        	if ((int)itr1.next() != (int)itr2.next()) {
		        		count++;
		        	}
		        	if (count > 1) {
		        		break;
		        	}
		        }
				
//				int A1[] = new int[b - a];
//				int A2[] = new int[d - c];
//				int k = 0;
//				for (int j = a; j < b; j++) {
//					insert(A1, A[j], k++);
//				}
//				k = 0;
//				for (int j = c; j < d; j++) {
//					insert(A2, A[j], k++);
//				}
//				int count = 0;
//				for (int j = 0; j < k; j++) {
//					if (A1[j] != A2[j]) {
//						count++;
//					}
//					if (count > 1) {
//						break;
//					}
//				}
				
//				CustomArrayList list1 = new CustomArrayList();
//				for (int j = a; j < b; j++) {
//					list1.insert(A[j]);
//				}
//				CustomArrayList list2 = new CustomArrayList();
//				for (int j = c; j < d; j++) {
//					list2.insert(A[j]);
//				}
//				if (list1.size() != list2.size()) {
//					System.out.println("NO");
//					continue;
//				}
//				int count = 0;
//				for (int j = 0; j < list1.size(); j++) {
//					if(list1.get(j) != list2.get(j)) {
//						count++;
//					}
//					if (count > 1) {
//						break;
//					}
//				}
				
//				SortedSet<Integer> sub1 = new TreeSet<>();
//				for (int j = a; j < b; j++) {
//					sub1.add(A[j]);
//				}
//				SortedSet<Integer> sub2 = new TreeSet<>();
//				for (int j = c; j < d; j++) {
//					sub2.add(A[j]);
//				}
//
//				Iterator<Integer> itr1 = sub1.iterator();
//				Iterator<Integer> itr2 = sub2.iterator();
//				int count = 0;
//			    while(itr1.hasNext()) {
//			    	if (itr1.next() != itr2.next()) {
//			    		count++;
//			    	}
//			    	if (count > 1) {
//			    		break;
//			    	}
//			    }
				if (count < 2) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
        }
	}
	
	private static class SkipList<T> {
		private final Comparator<T> comparator;
		private static final Object MINUS_INFINITY = new Object();
		private SkipListNode bottomList;
		private SkipListNode topList;
		private final Random random = new Random();

		public SkipList(final Comparator<T> comparator) {
			this.comparator = comparator;
			bottomList = topList = new SkipListNode((T) MINUS_INFINITY);
		}

		/**
		 * Adds the element to the bottom list and repeatedly flips a coin to decide
		 * whether to promote it a level up.
		 */
		public final boolean add(final T t) {
			SkipListNode newNode = new SkipListNode(t);
			LinkedList<SkipListNode> possiblePromotions = pathFromTopToImmediatelySmallerBottomNode(newNode);
			insertAndMaybePromote(newNode.entryValue, possiblePromotions, null);
			return true;
		}

		private void insertAndMaybePromote(T value,
				final LinkedList<SkipListNode> possiblePromotions,
				final SkipListNode lastCreatedNode) {
			SkipListNode newNode = new SkipListNode(value);
			if (possiblePromotions.isEmpty()) {
				SkipListNode newTop = createSingletonList(newNode, lastCreatedNode);
				promoteToToplist(newTop);
			} else {
				// horizontal insertion
				SkipListNode nodeToInsertAfter = possiblePromotions.pop();
				linkToPreviousNode(newNode, nodeToInsertAfter);
				// vertical insertion
				linkToNodeBelow(newNode, lastCreatedNode);
			}
			// The node we just created will be the node below for the next
			// promotion
			if (flipCoin()) {
				insertAndMaybePromote(value, possiblePromotions, newNode);
			}
		}

		private void linkToPreviousNode(final SkipListNode newNode,
				final SkipListNode nodeToInsertAfter) {
			SkipListNode next = nodeToInsertAfter.next;
			nodeToInsertAfter.next = newNode;
			newNode.prev = nodeToInsertAfter;
			newNode.next = next;
			if (next != null)
				next.prev = newNode;
		}

		private void linkToNodeBelow(final SkipListNode newNode,
				final SkipListNode below) {
			if (below != null) {
				below.up = newNode;
				newNode.down = below;
			}
		}

		private void promoteToToplist(final SkipListNode newTop) {
			newTop.down = topList;
			topList.up = newTop;
			topList = newTop;
		}

		private SkipListNode createSingletonList(final SkipListNode newNode,
				final SkipListNode below) {
			SkipListNode newTop = new SkipListNode((T) MINUS_INFINITY);
			newTop.next = newNode;
			newNode.prev = newTop;

			linkToNodeBelow(newNode, below);

			return newTop;
		}

		private boolean flipCoin() {
			return random.nextBoolean() && random.nextBoolean();
		}

		private LinkedList<SkipListNode> pathFromTopToImmediatelySmallerBottomNode(
				SkipListNode node) {
			SkipListNode list = topList;
			LinkedList<SkipListNode> promotions = new LinkedList<SkipListNode>();
			while (list != null) {
				while (list.next != null
						&& compare(list.next.entryValue, node.entryValue) <= 0)
					list = list.next;
				promotions.push(list);
				list = list.down;
			}
			return promotions;
		}

		private void updateAllTheWayDown(SkipListNode current, final T value) {
			while (current != null) {
				current.entryValue = value;
				current = current.down;
			}
		}

		public final boolean remove(final T t) {

			SkipListNode foundElement = findHighestLevelNodeWithValue(t);
			removeAllTheWayDown(foundElement);
			return foundElement != null;
		}

		/**
		 * Returns the highest-level node with the specified value if found, or
		 * null.
		 */
		private SkipListNode findHighestLevelNodeWithValue(final T t) {
			SkipListNode list = topList;
			while (list != null) {
				while (list.next != null && compare(list.next.entryValue, t) <= 0)
					list = list.next;
				if (compare(list.entryValue, t) == 0)
					return list;
				list = list.down;
			}
			return null;
		}

		public final T find(final T t) {
			SkipListNode foundNode = findHighestLevelNodeWithValue(t);
			return foundNode == null ? null : foundNode.entryValue;

		}

		final public void updateValue(T oldValue, T newValue) {
			SkipListNode highestLevelNodeWithValue = findHighestLevelNodeWithValue(oldValue);
			updateAllTheWayDown(highestLevelNodeWithValue, newValue);

		}

		private void removeAllTheWayDown(SkipListNode node) {
			if (node == null || node.prev == null)
				return;
			SkipListNode prev = node.prev;
			prev.next = node.next;
			if (node.next != null)
				node.next.prev = prev;
			removeAllTheWayDown(node.down);
		}

		public final boolean contains(final Object t) {
			SkipListNode foundElement = findHighestLevelNodeWithValue((T) t);
			return foundElement != null;
		}

		/**
		 * The size of the skiplist is just the size of the bottom list
		 */
		final int size() {
			int size = 0;
			SkipListNode current = bottomList.next; // there is a dummy element
			while (current != null) {
				size++;
				current = current.next;
			}
			return size;
		}

		/**
		 * All elements are part of the bottom list
		 */
		final boolean isEmpty() {
			return bottomList.next == null;
		}

		public final Iterator<T> iterator() {
			return new Iterator<T>() {
				SkipListNode currentNode = bottomList;

				@Override
				public boolean hasNext() {
					return currentNode.next != null;
				}

				@Override
				public T next() {
					if (currentNode.next == null)
						throw new NoSuchElementException();
					currentNode = currentNode.next;
					return currentNode.entryValue;
				}

				@Override
				public void remove() {
					SkipList.this.remove(currentNode.entryValue);
				}
			};
		}

		final int compare(final T o1, final T o2) {
			if (o1 == MINUS_INFINITY) {
				return -1;
			} else if (o2 == MINUS_INFINITY) {
				return 1;
			} else {
				return comparator.compare((T) o1, (T) o2);
			}
		}

		public final void clear() {
			topList = bottomList = new SkipListNode((T) MINUS_INFINITY);
		}

		class SkipListNode {
			T entryValue;
			SkipListNode up;
			SkipListNode down;
			SkipListNode next;
			SkipListNode prev;

			public SkipListNode(final T value) {
				this.entryValue = value;
			}

			public String toString() {
				return entryValue.toString();
			}
		}

		public final void uglyPrint() {
			SkipListNode list = topList;
			while (list != null) {
				printList(list);
				list = list.down;
			}
			System.out.println();
		}

		private final void printList(SkipListNode list) {
			SkipListNode current = list.next;
			while (current != null) {
				System.out.print(current.entryValue + "  ");
				current = current.next;
			}
			System.out.println();
		}

	}
	
	private static void insert(int A[], int x, int n) {
		if (n == 0) {
			A[0] = x;
			return;
		}
		int i = 0;
		while (i < n && A[i] < x)
			i++;
		for (int j = n; j > i; j--) {
			A[j] = A[j - 1];
		}
		A[i] = x;
	}
	
	static class CustomArrayList extends ArrayList<Integer> {
		private static final long serialVersionUID = 9083379952225638314L;
		public CustomArrayList() {
			super();
		}
		
		public void insert(int x) {
			// loop through all elements
		    for (int i = 0; i < size(); i++) {
		        // if the element you are looking at is smaller than x, 
		        // go to the next element
		        if (get(i) < x) continue;
		        // if the element equals x, return, because we don't add duplicates
		        // if (get(i) == x) return;
		        // otherwise, we have found the location to add x
		        add(i, x);
		        return;
		    }
		    // we looked through all of the elements, and they were all
		    // smaller than x, so we add ax to the end of the list
		    add(x);
		}
		
		public String toString(){
	        if(super.size() == 0) 
	        	return "[]";
	        String s = "[" + super.get(0).toString();
	        for(int i = 1; i < super.size(); i++){
	            s += ", " + super.get(i).toString();
	        }
	        return s += "]";
	    }
		
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