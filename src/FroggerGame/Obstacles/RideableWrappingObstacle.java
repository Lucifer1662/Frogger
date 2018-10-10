package FroggerGame.Obstacles;

import FroggerGame.Components.WrappingMovement;
import FroggerGame.Components.CollisionComponents.OnCollisionAddRider;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;

/**
 * A rideable wrapping obstacle
 * 
 * @author Luke Hawkins
 *
 */
public abstract class RideableWrappingObstacle extends RideableObstacle {

	/**
	 * Constructs a rideable wrapping obstacle
	 * 
	 * @param scene         The scene in
	 * @param speed         The speed set to
	 * @param isLeftToRight The direction going
	 * @param width         The width of the obstacle
	 * @param height        The height of the obstacle
	 * @param imageLocation The image location
	 */
	public RideableWrappingObstacle(Scene scene, float speed, boolean isLeftToRight, float width, float height,
			String imageLocation) {
		super(scene, width, height, imageLocation);
		new OnCollisionAddRider(this);
		new WrappingMovement(this, Window.getPixelToUnit(scene) * speed * (isLeftToRight ? 1 : -1), width / 2);
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
