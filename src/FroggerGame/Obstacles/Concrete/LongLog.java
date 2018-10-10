package FroggerGame.Obstacles.Concrete;

import GameEngine.Scene;
import GameEngine.Window;

/**
 * A long log
 * 
 * @author lhawk
 *
 */
public class LongLog extends Log {
	private static final String IMAGE_LOCATION = "assets/longlog.png";
	// speed in pixels per second
	private static final float SPEED = 70;
	private static final float WIDTH = 4.75f, HEIGHT = 1.0f;

	/**
	 * Constructs a long log
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public LongLog(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
	}

}
