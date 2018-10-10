package FroggerGame.Obstacles.Concrete;

import FroggerGame.Obstacles.SimpleWrappingObstacle;
import GameEngine.Scene;

/**
 * A bus
 * 
 * @author lhawk
 *
 */
public class Bus extends SimpleWrappingObstacle {

	private static final String IMAGE_LOCATION = "assets/bus.png";
	// speed in pixels per second
	private static final float SPEED = 150;
	private static final float WIDTH = 1.0f, HEIGHT = 1.0f;

	/**
	 * Constructs a bus in a scene, going in a direction
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public Bus(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
	}

	@Override
	public boolean isSafe() {
		return false;
	}

}
