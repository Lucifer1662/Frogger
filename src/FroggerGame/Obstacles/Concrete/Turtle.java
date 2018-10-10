package FroggerGame.Obstacles.Concrete;

import FroggerGame.Components.DisappearReappear;
import FroggerGame.Obstacles.RideableWrappingObstacle;
import GameEngine.Scene;

/**
 * A turtle obstacle
 * 
 * @author lhawk
 *
 */
public class Turtle extends RideableWrappingObstacle {
	private static final String IMAGE_LOCATION = "assets/turtles.png";
	// speed in pixels per second
	private static final float SPEED = 85;
	private static final float WIDTH = 3.0f, HEIGHT = 0.5f, VISIBLE_DURATION = 7.0f, INVISIBLE_DURATION = 2.0f;
	private DisappearReappear dissappearReappear;

	/**
	 * Constructs a turtle in a scene, going left or right
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight Whether the turtle will go left or right
	 */
	public Turtle(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
		dissappearReappear = new DisappearReappear(this, getSprite(), VISIBLE_DURATION, INVISIBLE_DURATION);
	}

	@Override
	public boolean isSafe() {
		return dissappearReappear.getVisiblity();
	}

}
