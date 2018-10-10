package FroggerGame.Components.CollisionComponents;

import FroggerGame.Frog.Frog;
import FroggerGame.Managers.FrogEndManager;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A component that notify's a frog end manager when a collision with a frog
 * occurs
 * 
 * @author Luke Hawkins
 *
 */
public class OnCollisionWithFrogUpdateFrogEndManager extends Component implements OnCollisionable {
	private FrogEndManager manager;
	private boolean frogHasCollided = false;

	/**
	 * Constructs an OnCollisionWithFrogUpdateFrogEndManager attached to a game
	 * object, and associated with a frog end manager
	 * 
	 * @param gameObject The parent
	 * @param manager    The manager to be notified
	 */
	public OnCollisionWithFrogUpdateFrogEndManager(GameObject gameObject, FrogEndManager manager) {
		super(gameObject);
		this.manager = manager;
	}

	/**
	 * The on collision enter event
	 * 
	 * @param collider The collider that was collided with
	 */
	@Override
	public void onCollisionEnter(Collider collider) {
		if (collider.getGameObject() instanceof Frog) {
			Frog frog = (Frog) collider.getGameObject();
			if (frogHasCollided) {
				frog.removeLife();
			}
			manager.addFrogEndCompleted();
			frogHasCollided = true;
			frog.Reset();

		}
	}

}
