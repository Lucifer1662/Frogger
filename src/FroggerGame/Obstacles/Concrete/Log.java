package FroggerGame.Obstacles.Concrete;

import FroggerGame.Obstacles.RideableWrappingObstacle;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/**
 * A log
 * 
 * @author lhawk
 *
 */
public class Log extends RideableWrappingObstacle {
	private static final String IMAGE_LOCATION = "assets/log.png";
	// speed in pixels per second
	private static final float SPEED = 100;// 100;
	private static final float WIDTH = 2.75f, HEIGHT = 1.0f;

	/**
	 * Constructs a log in a scene
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public Log(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
	}

	/**
	 * Constructs a log
	 * 
	 * @param scene         The scene in
	 * @param speed         The speed of the log
	 * @param isLeftToRight The direction going
	 * @param width         The width of the log
	 * @param height        The height of the log
	 * @param imageLocation The image location of the image to be rendered
	 */
	public Log(Scene scene, float speed, boolean isLeftToRight, float width,
			float height, String imageLocation) {
		super(scene, speed, isLeftToRight, width, height, imageLocation);
	}

}
