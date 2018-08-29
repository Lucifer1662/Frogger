package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;


public abstract class FrogInput extends Component {



	public FrogInput(GameObject gameObject) {
		super(gameObject);
	}

	public Vector2f GetMovenent() {
		return null;
	}
}
