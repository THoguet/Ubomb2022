package fr.ubx.poo.ubomb.graph;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	private final T data;
	private final List<Node<T>> neighbours;

	public Node(T data) {
		this.data = data;
		this.neighbours = new ArrayList<>();
	}

	public T getData() {
		return this.data;
	}

	public void addEdge(Node<T> to) {
		this.neighbours.add(to);
	}

	public List<Node<T>> getNeighbours() {
		return this.neighbours;
	}
}