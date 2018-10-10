package FroggerGame.Components.CollisionComponents;

import FroggerGame.Interfaces.Rider;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A component that when on collision with a rider, it adds the rider to itself
 * 
 * @author Luke Hawkins
 *
 */
public class OnCollisionAddRider extends Component implements OnCollisionable {

	/**
	 * Construct the component
	 * 
	 * @param gameObject The parent
	 */
	public OnCollisionAddRider(GameObject gameObject) {
		super(gameObject);
	}

	@Override
	public void onCollisionEnter(Collider collider) {
		if (collider.getGameObject() instanceof Rider) {
			((Rider) collider.getGameObject()).mount(getGameObject());
		}
	}

	@Override
	public void onCollisionExit(Collider collider) {
		if (collider.getGameObject() instanceof Rider) {
			((Rider) collider.getGameObject()).dismount(getGameObject());
		}
	}

}
