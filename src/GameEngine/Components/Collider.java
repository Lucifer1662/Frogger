package GameEngine.Components;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;

/*
 * Used to represent an abstract collider
 *  that can collide with other colliders
 */
public abstract class Collider extends Component{
	
	public Collider(GameObject gameObject) {
		super(gameObject);
		if(GameObject.canGetScene(getGameObject()))
			getScene().getColliderSpace().add(this);
	}
	
	/*
	 * Test collision with another collider
	 */
	public abstract boolean testCollision(Collider col);
}
