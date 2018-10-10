package FroggerGame.Obstacles.Concrete;

import FroggerGame.Obstacles.SimpleWrappingObstacle;
import GameEngine.Scene;

/**
 * A race car
 * 
 * @author Luke Hawkins
 *
 */
public class RaceCar extends SimpleWrappingObstacle {

	private static final String IMAGE_LOCATION = "assets/racecar.png";
	// speed in pixels per second
	private static final float SPEED = 500;
	private static final float WIDTH = 1.0f, HEIGHT = 1.0f;

	/**
	 * Constructs a race car
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public RaceCar(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
	}

}
