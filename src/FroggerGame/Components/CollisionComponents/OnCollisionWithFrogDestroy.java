package FroggerGame.Components.CollisionComponents;

import FroggerGame.Frog.Frog;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A compone that when it collides with a frog it destroys the parent it is
 * attached to
 * 
 * @author lhawk
 *
 */
public class OnCollisionWithFrogDestroy extends Component implements OnCollisionable {

	/**
	 * Constructs the on Collision with frog destroy
	 * 
	 * @param gameObject The parent object, that will be destroyed when collision
	 *                   with a frog
	 */
	public OnCollisionWithFrogDestroy(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollisionEnter(Collider collider) {
		if (collider.getGameObject() instanceof Frog)
			getGameObject().Destroy();
	}

}
