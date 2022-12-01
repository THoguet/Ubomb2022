package fr.ubx.poo.ubomb.pathfinder;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.graph.Node;

public class PathNode extends Node<Position> {
	private PathNode parent;
	private double distanceStart;
	private double distanceTarget;
	private double distanceStartTarget;

	public PathNode(Node<Position> start, PathNode parent, Position target) {
		super(start.getData());
		this.parent = parent;
		updateDistanceStart();
		updateDistanceTarget(target);
		for (Node<Position> neig : start.getNeighbours()) {
			this.addEdge(neig);
		}
	}

	private void updateDistanceStartTarget() {
		this.distanceStartTarget = this.distanceStart + this.distanceTarget;
	}

	public double getDistanceStart() {
		return distanceStart;
	}

	public int getX() {
		return this.getData().x();
	}

	public int getY() {
		return this.getData().y();
	}

	public double getDistanceTarget() {
		return distanceTarget;
	}

	public double getDistanceStartTarget() {
		return distanceStartTarget;
	}

	private double getUpdatedDistanceStart() {
		if (parent == null)
			return 0;
		return super.getData().distance(parent.getData()) + parent.getUpdatedDistanceStart();
	}

	private void updateDistanceStart() {
		this.distanceStart = this.getUpdatedDistanceStart();
		updateDistanceStartTarget();
	}

	private void updateDistanceTarget(Position target) {
		this.distanceTarget = super.getData().distance(target);
		updateDistanceStartTarget();
	}

	public PathNode getParent() {
		return parent;
	}

	public boolean isArrived() {
		return distanceTarget == 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PathNode))
			return false;
		return this.getData().equals(((PathNode) obj).getData());
	}
}
