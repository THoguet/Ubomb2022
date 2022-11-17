package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Movable;

public class Box extends Decor implements Movable {
	public Box(Position position) {
		super(position);
	}

	public boolean canMove(Direction direction){
		boolean canMove;
		int dx = 0, dy = 0;
		switch (direction) {
			case UP ->{
				dy--;
				//canMove = game.getGameObjects(new Position()) != 0;
			}
			case DOWN ->{
				dy++;
				canMove = game.player().getPosition().y() != game.grid().height() - 1;
			}
			case LEFT ->{
				dx--;
				canMove = game.player().getPosition().x() != 0;
			}
			case RIGHT ->{
				dx++;
				canMove = game.player().getPosition().x() != game.grid().width() - 1;
			}
			default ->
					throw new RuntimeException("direction in the 3rd dimension");
		}
		return true;
		//return canMove;
		//if (new Position(this.getPosition().x() + dx, this.getPosition().y() + dy).get )
	}

	public void doMove(Direction direction){
		return;
	}
}
