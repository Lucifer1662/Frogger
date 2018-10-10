package FroggerGame.Components.CollisionComponents;

import FroggerGame.Interfaces.Alive;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A component that adds a life when it collides with something that is alive
 * 
 * @author Luke Hawkins
 *
 */
public class OnCollisionAddLife extends Component implements OnCollisionable {

	/**
	 * Constructs on collision add life
	 * 
	 * @param gameObject
	 */
	public OnCollisionAddLife(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollisionEnter(Collider collider) {
		if (collider.getGameObject() instanceof Alive)
			((Alive) collider.getGameObject()).addLife();
	}

}
