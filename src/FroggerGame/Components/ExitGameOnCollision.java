package FroggerGame.Components;

import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/*
 * Exits the game on collision
 */
public class ExitGameOnCollision extends Component implements OnCollisionable {

	public ExitGameOnCollision(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollision(Collider collider) {
		Window.getWindow().exit();
	}

}
