package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;

public class Bomb extends GameObject {

	public final static long DURATIONBEFOREEXPLOTION = 4000;
	public static final int NBIMAGE = 4;

	private final Timer timerExplosion = new Timer(DURATIONBEFOREEXPLOTION);
	private final int rangeExplosion;
	private int lastImageId;
	private boolean exploded = false;

	public Bomb(Position position, int rangeExplosion) {
		super(null, position);
		this.rangeExplosion = rangeExplosion;
		this.timerExplosion.start();
	}

	public Timer getTimerExplosion() {
		return timerExplosion;
	}

	public int getRangeExplosion() {
		return rangeExplosion;
	}

	@Override
	public void explode() {
		this.exploded = true;
	}

	public int getImageId() {
		long durationEachImage = Bomb.DURATIONBEFOREEXPLOTION / NBIMAGE;
		int imageId = NBIMAGE - 1;
		for (; imageId > 0; imageId--) {
			if (this.getTimerExplosion().remaining() > durationEachImage * imageId)
				break;
		}
		return imageId;
	}

	public boolean isExploded() {
		return exploded;
	}

	public void update(long now) {
		this.timerExplosion.update(now);
		if (!this.timerExplosion.isRunning()) {
			this.exploded = true;
		} else {
			int imageId = this.getImageId();
			if (imageId != this.lastImageId) {
				this.lastImageId = imageId;
				this.setModified(true);
			}
		}
	}
}
