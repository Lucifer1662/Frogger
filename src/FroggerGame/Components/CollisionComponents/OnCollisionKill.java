package FroggerGame.Components.CollisionComponents;

import FroggerGame.Interfaces.Alive;
import FroggerGame.Obstacles.Obstactle;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A class that kills its associate when it is in an unsafe area
 */
public class OnCollisionKill extends Component implements OnCollisionable {
	private Alive alive;

	/**
	 * Constructs an on collision kill
	 * 
	 * @param gameObject The parent
	 * @param alive      The object to kill
	 */
	public OnCollisionKill(GameObject gameObject, Alive alive) {
		super(gameObject);
		this.alive = alive;
	}

	@Override
	public void onCollisionEnter(Collider collider) {
		onColliding(collider);
	}

	@Override
	public void onColliding(Collider collider) {
		if (collider.getGameObject() instanceof Obstactle && !((Obstactle) collider.getGameObject()).isSafe()) {
			alive.removeLife();
		}

	}

}
