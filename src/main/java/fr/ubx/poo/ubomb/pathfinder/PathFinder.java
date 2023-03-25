package fr.ubx.poo.ubomb.pathfinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.graph.Node;

public class PathFinder {
	private final Position target;
	private final ConcurrentSkipListSet<PathNode> notEvaluated;
	private final HashSet<PathNode> Evaluated;

	public PathFinder(Node<Position> start, Position target) {
		PathNode start1 = new PathNode(start, null, target);
		this.target = target;
		this.notEvaluated = new ConcurrentSkipListSet<>(
				Comparator.comparingDouble(PathNode::getDistanceStartTarget)
						.thenComparingDouble(PathNode::getDistanceTarget).thenComparingInt(PathNode::getX)
						.thenComparingInt(PathNode::getY));
		this.Evaluated = new HashSet<>();
		this.notEvaluated.add(start1);
	}

	private List<Position> getPathParent(PathNode p, List<Position> ret) {
		ret.add(p.getData());
		if (p.getParent() != null)
			return getPathParent(p.getParent(), ret);
		return ret;
	}

	public PathNode getElementNotEvaluated(PathNode p) {
		for (PathNode pathNode : notEvaluated) {
			if (pathNode.equals(p))
				return pathNode;
		}
		return null;
	}

	public List<Position> findPath() {
		while (notEvaluated.size() != 0) {
			PathNode current = notEvaluated.first();
			notEvaluated.remove(current);
			Evaluated.add(current);

			if (current.isArrived())
				return getPathParent(current, new ArrayList<>());

			for (Node<Position> neighbour : current.getNeighbours()) {
				PathNode newOne = new PathNode(neighbour, current, target);
				boolean isAlreadyEvaluated = false;
				for (PathNode eva : Evaluated) {
					if (eva.equals(newOne)) {
						isAlreadyEvaluated = true;
						break;
					}
				}
				if (isAlreadyEvaluated)
					continue;
				PathNode alreadyKnow = getElementNotEvaluated(newOne);
				if (alreadyKnow == null) {
					notEvaluated.add(newOne);
				} else if (alreadyKnow.getDistanceStartTarget() > newOne.getDistanceStartTarget()) {
					notEvaluated.remove(alreadyKnow);
					notEvaluated.add(newOne);
				}
			}
		}
		return null;
	}
}
