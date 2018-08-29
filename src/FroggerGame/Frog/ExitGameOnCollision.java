package FroggerGame.Frog;

import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollideable;

public class ExitGameOnCollision extends Component implements OnCollideable {

	public ExitGameOnCollision(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollision(Collider collider) {
		Window.getWindow().exit();
	}

}
