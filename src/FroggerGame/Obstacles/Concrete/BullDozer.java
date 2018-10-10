package FroggerGame.Obstacles.Concrete;

import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Interfaces.Pushable;
import FroggerGame.Obstacles.SimpleWrappingObstacle;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Collider;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A bull dozer
 * 
 * @author Luke Hawkins
 *
 */
public class BullDozer extends SimpleWrappingObstacle implements OnCollisionable {

	private static final String IMAGE_LOCATION = "assets/bulldozer.png";
	// speed in pixels per second
	private static final float SPEED = 50;
	private static final float WIDTH = 1.0f, HEIGHT = 1.0f;

	/**
	 * Constructs a bull dozer
	 * 
	 * @param scene         The scene in
	 * @param isLeftToRight The direction going
	 */
	public BullDozer(Scene scene, boolean isLeftToRight) {
		super(scene, SPEED, isLeftToRight, WIDTH, HEIGHT, IMAGE_LOCATION);
	}

	@Override
	public void onColliding(Collider col) {
		if (col.getGameObject() instanceof Pushable) {
			((Pushable) col.getGameObject())
					.push(new Vector2f(Window.getPixelToUnit(getScene()) * SPEED * getGame().getTimeDelta(), 0));
		}
	}

	@Override
	public boolean isSafe() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
