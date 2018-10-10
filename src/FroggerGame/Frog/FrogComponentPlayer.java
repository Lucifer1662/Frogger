package FroggerGame.Frog;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Obstacles.Obstactle;
import FroggerGame.Obstacles.Concrete.BullDozer;
import FroggerGame.Obstacles.Concrete.Log;
import FroggerGame.Tiles.Water;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;
import GameEngine.CoreInterfaces.Updateable;

/**
 * A frog component that can be controlled by the player
 */
public class FrogComponentPlayer extends FrogComponent implements Updateable {

	/**
	 * Constructs the frog component that is controlled by the player
	 * 
	 * @param gameObject The parent
	 */
	public FrogComponentPlayer(GameObject gameObject) {
		super(gameObject);
	}

	/**
	 * Gets the movement for the frog to move
	 * 
	 * @return The direction to move
	 */
	@Override
	public Vector2f GetMovenent() {
		Vector2f dir = new Vector2f();
		Input input = Window.getWindow().getInput();
		// get direction from the keys
		if (input.isKeyPressed(Input.KEY_UP))
			dir.y = 1;
		else if (input.isKeyPressed(Input.KEY_DOWN))
			dir.y = -1;
		else if (input.isKeyPressed(Input.KEY_LEFT))
			dir.x = -1;
		else if (input.isKeyPressed(Input.KEY_RIGHT))
			dir.x = 1;

		return dir;
	}

}
