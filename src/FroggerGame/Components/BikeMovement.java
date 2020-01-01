package FroggerGame.Components;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/**
 * The movement of the bike
 * 
 * @author lhawk
 *
 */
public class BikeMovement extends Component implements Updateable {
	private float speed, lowerBarrier, upperBarrier;

	/**
	 * Constructs the bike movement component
	 * 
	 * @param gameObject    The parent
	 * @param speed         The speed
	 * @param isLeftToRight The direction
	 * @param lowerBarrier  The distance from 0 when the bike turns around when
	 *                      less than 0
	 * @param uppderBarrier The distance from 0 when the bike turns around when
	 *                      greater than 0
	 */
	public BikeMovement(GameObject gameObject, float speed,
			boolean isLeftToRight, float lowerBarrier, float uppderBarrier) {
		super(gameObject);
		this.speed = speed * (isLeftToRight ? 1 : -1);
		this.lowerBarrier = lowerBarrier;
		this.upperBarrier = uppderBarrier;
	}

	@Override
	public void update() {
		Vector2f pos = getGameObject().getTransform().getPosition();
		if (pos.x < lowerBarrier)
			speed = Math.abs(speed);
		if (pos.x > upperBarrier)
			speed = -Math.abs(speed);

		pos.x += speed * getGame().getTimeDelta();
		getGameObject().getTransform().setPosition(pos);
	}

}
