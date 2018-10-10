package FroggerGame.Obstacles.Concrete;

import FroggerGame.Components.BikeMovement;
import FroggerGame.Obstacles.Obstactle;
import GameEngine.Scene;

/**
 * A bike obstacle
 * 
 * @author Luke Hawkins
 *
 */
public class Bike extends Obstactle {

	private static final String IMAGE_LOCATION = "assets/bike.png";
	// speed in pixels per second
	private static final float SPEED = 200 / 48.0f, WIDTH = 1.0f, HEIGHT = 1.0f, LOWER_BARRIER = 0.5f - 21 / 2.0f,
			UPPER_BARRIER = 1000 / 48.0f - 21 / 2.0f;

	/**
	 * Constructs a bike
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public Bike(Scene scene, boolean isLeftToRight) {
		super(scene, WIDTH, HEIGHT, IMAGE_LOCATION);
		new BikeMovement(this, SPEED, isLeftToRight, LOWER_BARRIER, UPPER_BARRIER);
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
