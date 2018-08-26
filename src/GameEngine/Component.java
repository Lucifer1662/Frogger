package GameEngine;
import org.newdawn.slick.SlickException;

import GameEngine.Collision.Collider;
import GameEngine.Collision.ICollision;

public abstract class Component implements ICollision, IOnRootChanged {
	//gameObject is not private because i want it to be shared with GameObject
	//so it can be initiated without putting it through the constructor
	//because when inherited from the subclass has that control
	//which i don't want
	
	private GameObject gameObject;	
	@Override
	public void onCollision(Collider col) {}
	@Override
	public void onRootChanged(GameObject parent) {}
	public void render() throws SlickException {}
	public void update() throws SlickException {}
		
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObjectAttachedTo(GameObject gameObject) {
		if(this.gameObject != null)
			this.gameObject.removeComponent(this);
		
		this.gameObject = gameObject;
	}
	
	
		
}
