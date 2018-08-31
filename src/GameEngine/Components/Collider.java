package GameEngine.Components;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;

public abstract class Collider extends Component{
	public Collider(GameObject gameObject) {
		super(gameObject);
		if(GameObject.canGetScene(getGameObject()))
			getScene().getColliderSpace().add(this);
	}
	
	public abstract boolean testCollision(Collider col);
}
