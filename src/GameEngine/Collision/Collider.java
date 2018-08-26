package GameEngine.Collision;

import GameEngine.Component;
import GameEngine.GameObject;

public abstract class Collider extends Component{
	public Collider() {
		if(GameObject.canGetScene(getGameObject()))
			getGameObject().getScene().getColliderSpace().add(this);
	}
	
	@Override
	public void finalize() {
		getGameObject().getScene().getColliderSpace().remove(this);
	}
	
	
	@Override
	public void setGameObjectAttachedTo(GameObject gameObject) {
		onRootChanged(gameObject);
		super.setGameObjectAttachedTo(gameObject);
	}
	
	@Override
	public void onRootChanged(GameObject gameObject) {
		if(GameObject.canGetScene(gameObject) && GameObject.canGetScene(getGameObject())) {
			if(gameObject.getScene() == getGameObject().getScene())
				return;
		}
		removeFromCurrentColliderSpace();
		addToGameObjectsSceneColliderSpace(gameObject);
	}
	
	private void removeFromCurrentColliderSpace() {
		if(GameObject.canGetScene(getGameObject()))
			getGameObject().getScene().getColliderSpace().remove(this);
	}
	
	private void addToGameObjectsSceneColliderSpace(GameObject gameObject) {
		if(GameObject.canGetScene(gameObject))
			gameObject.getScene().getColliderSpace().add(this);
	}
	
	public abstract boolean testCollision(Collider col);
	
}
