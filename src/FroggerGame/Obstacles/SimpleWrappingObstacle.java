package FroggerGame.Obstacles;

import FroggerGame.Components.WrappingMovement;
import GameEngine.Scene;
import GameEngine.Window;

/**
 * A base class the create simple wrapping obstacles
 * 
 * @author Luke Hawkins
 *
 */
public abstract class SimpleWrappingObstacle extends Obstactle {

	/**
	 * Constructs a simple wrapping obstacle
	 * 
	 * @param scene         The scene in
	 * @param speed         The speed
	 * @param isLeftToRight Direction going
	 * @param width         The width of the obstacle
	 * @param height        The height of the obstacle
	 * @param imageLocation The image location of the obstacle
	 */
	public SimpleWrappingObstacle(Scene scene, float speed,
			boolean isLeftToRight, float width, float height,
			String imageLocation) {
		super(scene, width, height, imageLocation);
		new WrappingMovement(this,
				Window.getPixelToUnit(scene) * speed * (isLeftToRight ? 1 : -1),
				width / 2);
	}

	@Override
	public boolean isSafe() {
		return false;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

}
