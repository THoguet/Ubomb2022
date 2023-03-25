package fr.ubx.poo.ubomb.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph<T> {
	private final Set<Node<T>> nodes;

	public Graph() {
		this.nodes = new HashSet<>();
	}

	public void addNode(T data) {
		this.nodes.add(new Node<>(data));
	}

	public Node<T> getNode(T data) {
		return this.nodes.stream().filter(n -> n.getData().equals(data)).findFirst().get();
	}

}