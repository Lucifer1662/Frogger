package FroggerGame.Obstacles.Concrete;

import FroggerGame.Components.CollisionComponents.OnCollideWithFrogMakeSpriteVisible;
import FroggerGame.Components.CollisionComponents.OnCollisionWithFrogUpdateFrogEndManager;
import FroggerGame.Managers.FrogEndManager;
import FroggerGame.Obstacles.Obstactle;
import GameEngine.Scene;

/**
 * A frog end spot, the goal of the game
 * 
 * @author Luke Hawkins
 *
 */
public class FrogEnd extends Obstactle {
	private static float WIDTH = 2, HEIGHT = 1;
	private static String IMAGE_LOCATION = "assets/frog.png";

	/**
	 * Constructs a frog end in a scene, associated to a manager
	 * 
	 * @param scene   The scene in
	 * @param manager The manager associated with
	 */
	public FrogEnd(Scene scene, FrogEndManager manager) {
		super(scene, WIDTH, HEIGHT, IMAGE_LOCATION);
		new OnCollideWithFrogMakeSpriteVisible(this, getSprite());
		new OnCollisionWithFrogUpdateFrogEndManager(this, manager);
		getSprite().setWidth(1);
		getSprite().setVisibility(false);
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
