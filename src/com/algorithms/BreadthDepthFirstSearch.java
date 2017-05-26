package com.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
 * http://www.java2blog.com/2015/12/breadth-first-search-in-java.html
 */

public class BreadthDepthFirstSearch {
	private Queue<Node> queue;
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	static class Node {
		int data;
		boolean visited;
		Node prev;
		
		Node(int data) {
			this.data = data;
		}
		
		Node getPrevNode() {
			return prev;
		}
		
		public String toString() {
			return Integer.toString(data);
		}
	}

	public BreadthDepthFirstSearch() {
		queue = new LinkedList<Node>();
	}

	// find neighbors of node using adjacency matrix
	// if adjacency_matrix[i][j]==1, then nodes at index i and index j are
	// connected
	public List<Node> findNeighbours(int adjacency_matrix[][], Node x) {
		int nodeIndex = -1;

		List<Node> neighbours = new ArrayList<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).equals(x)) {
				nodeIndex = i;
				break;
			}
		}

		if (nodeIndex != -1) {
			for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
				if (adjacency_matrix[nodeIndex][j] == 1) {
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}

	public boolean bfs(int adjacency_matrix[][], Node start, Node end) {
		queue.add(start);
		start.visited = true;
		List<Node> trace = new ArrayList<>();
		trace.add(start);
		while (!queue.isEmpty()) {
			Node element = queue.remove();
			if (element.equals(end)) {
				return true;
			}
			List<Node> neighbours = findNeighbours(adjacency_matrix, element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					queue.add(n);
					n.visited = true;
					n.prev = element;
				}
			}
		}
		return false;
	}
	
	public boolean dfsUsingStack(int adjacency_matrix[][], Node startNode, Node endNode) {
		Stack<Node> stack = new Stack<Node>();
		stack.add(startNode);
		startNode.visited = true;
		while (!stack.isEmpty()) {
			Node element = stack.pop();
			if (element.equals(endNode)) {
				return true;
			}
			List<Node> neighbours = findNeighbours(adjacency_matrix, element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node n = neighbours.get(i);
				if (n != null && !n.visited) {
					stack.add(n);
					n.visited = true;
					n.prev = element;
				}
			}
		}
		return false;
	}
	
	private void printSearchPath(Node startNode, Node endNode) {
		Node curr = endNode;
		while(!curr.equals(startNode)) {		
			System.out.print(curr.toString() + " <- ");
			curr = curr.prev;
		}
		System.out.print(startNode);
	}

	public static void main(String arg[]) {
		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);

		nodes.add(node40);
		nodes.add(node10);
		nodes.add(node20);
		nodes.add(node30);
		nodes.add(node50);
		nodes.add(node60);
		nodes.add(node70);
		int adjacency_matrix[][] = { 
				{ 0, 1, 1, 0, 0, 0, 0 }, // Node 1: 40
				{ 0, 0, 0, 1, 0, 0, 0 }, // Node 2 :10
				{ 0, 1, 0, 1, 1, 1, 0 }, // Node 3: 20
				{ 0, 0, 0, 0, 1, 0, 0 }, // Node 4: 30
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 5: 60
				{ 0, 0, 0, 0, 0, 0, 1 }, // Node 6: 50
				{ 0, 0, 0, 0, 0, 0, 0 }, // Node 7: 70
		};
		
		BreadthDepthFirstSearch bfsExample = new BreadthDepthFirstSearch();
		Node startNode = node40;
		Node endNode = node70;
		if(bfsExample.bfs(adjacency_matrix, startNode, endNode)) {
			System.out.print("BFS search path:");
			bfsExample.printSearchPath(node40, node70);
		}

//		if(bfsExample.dfsUsingStack(adjacency_matrix, startNode, endNode)) {
//			System.out.print("DFS search path:");
//			bfsExample.printSearchPath(startNode, endNode);
//		}
		
	}
	
	
}