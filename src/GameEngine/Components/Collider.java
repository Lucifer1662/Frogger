package GameEngine.Components;

import GameEngine.GameObjects.GameObject;

public abstract class Collider extends Component{
	public Collider(GameObject gameObject) {
		super(gameObject);
		if(GameObject.canGetScene(getGameObject()))
			getScene().getColliderSpace().add(this);
	}
	
	public abstract boolean testCollision(Collider col);
	
}
