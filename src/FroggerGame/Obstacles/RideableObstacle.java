package FroggerGame.Obstacles;

import FroggerGame.Components.CollisionComponents.OnCollisionAddRider;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;

/**
 * A rideable obstacle that rider's can ride
 * 
 * @author lhawk
 *
 */
public abstract class RideableObstacle extends Obstactle {

	/**
	 * Constructs a rideable obstacle
	 * 
	 * @param scene         The scene in
	 * @param width         The width
	 * @param height        The height
	 * @param imageLocation The image location
	 */
	public RideableObstacle(Scene scene, float width, float height,
			String imageLocation) {
		super(scene, width, height, imageLocation);
		new OnCollisionAddRider(this);
	}

	@Override
	public boolean isSafe() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

}
