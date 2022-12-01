package fr.ubx.poo.ubomb.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph<T> {
	private final Set<Node<T>> nodes;

	public Graph() {
		this.nodes = new HashSet<>();
	}

	public void addNode(T data) {
		this.nodes.add(new Node<T>(data));
	}

	public Node<T> getNode(T data) {
		return this.nodes.stream().filter(n -> n.getData().equals(data)).findFirst().get();
	}

	public Set<Node<T>> getNodes() {
		return this.nodes;
	}

	public boolean isConnected() {
		Set<Node<T>> marked = new HashSet<>();
		Node<T> node = nodes.iterator().next();
		neighbours(node, marked);
		return marked.size() == nodes.size();
	}

	private void neighbours(Node<T> n, Set<Node<T>> marked) {
		marked.add(n);
		for (Node<T> node : n.getNeighbours()) {
			if (!marked.contains(node))
				neighbours(node, marked);
		}
	}
}