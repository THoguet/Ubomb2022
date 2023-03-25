package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.Movable;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.Takeable;
import fr.ubx.poo.ubomb.go.WalkVisitor;

public abstract class Chara extends GameObject implements Movable, WalkVisitor, TakeVisitor {
	private final static long DURATION_BLINKING = 500;
	private Direction direction;
	private final Timer blinkingTimer = new Timer(DURATION_BLINKING);
	private final Timer notBlinkingTimer = new Timer(DURATION_BLINKING);
	private boolean blinking = false;
	private boolean moveRequested = false;
	private final Timer invincibilityTimer;
	private int lives;
	private Position movedFrom = null;

	public Chara(Game game, Position position, int lives, Timer timer) {
		super(game, position);
		this.direction = Direction.DOWN;
		this.invincibilityTimer = timer;
		this.lives = lives;
	}

	public void requestMove(Direction direction) {
		if (direction != this.direction) {
			this.direction = direction;
			setModified(true);
		}
		moveRequested = true;
	}

	public boolean isInvincible() {
		return this.getInvincibilityTimer().isRunning();
	}

	public Timer getInvincibilityTimer() {
		return invincibilityTimer;
	}

	// Lives
	public int getLives() {
		return lives;
	}

	public void addLives(int delta) {
		lives += delta;
	}

	public Position getMovedFrom() {
		return movedFrom;
	}

	public void setMovedFrom(Position movedFrom) {
		this.movedFrom = movedFrom;
	}

	@Override
	public void doMove(Direction direction) {
		// This method is called only if the move is possible, do not check again
		this.movedFrom = this.getPosition();
		Position nextPos = direction.nextPosition(getPosition());
		setPosition(nextPos);
		GameObject next = game.grid().get(nextPos);
		if (next instanceof Takeable taken)
			taken.takenBy(this);
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean isBlinking() {
		return blinking;
	}

	public void update(long now) {
		if (lives <= 0)
			this.remove();
		this.invincibilityTimer.update(now);
		this.blinkingTimer.update(now);
		this.notBlinkingTimer.update(now);
		if (invincibilityTimer.isRunning()) {
			if (!this.blinking && !this.notBlinkingTimer.isRunning() && !this.blinkingTimer.isRunning()) {
				this.blinkingTimer.start();
				this.blinking = true;
				this.setModified(true);
			} else if (this.blinking && !this.blinkingTimer.isRunning() && !this.notBlinkingTimer.isRunning()) {
				this.notBlinkingTimer.start();
				this.blinking = false;
				this.setModified(true);
			}
		} else if (blinking) {
			this.blinking = false;
			this.setModified(true);
		}
		if (moveRequested && canMove(direction)) {
			doMove(direction);
		}
		moveRequested = false;
	}

}
