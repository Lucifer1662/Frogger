package FroggerGame.Frog;

import GameEngine.Components.Collider;
import GameEngine.Components.Component;
import GameEngine.CoreInterfaces.OnCollideable;
import GameEngine.GameObjects.GameObject;

public class ExitGameOnCollision extends Component implements OnCollideable {

	public ExitGameOnCollision(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollision(Collider collider) {
			getWindow().exit();
	}

}
