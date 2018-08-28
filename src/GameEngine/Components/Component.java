package GameEngine.Components;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.CoreInterfaces.IOnRootChanged;
import GameEngine.CoreInterfaces.OnCollideable;
import GameEngine.GameObjects.GameObject;

public abstract class Component implements OnCollideable, IOnRootChanged {
	//gameObject is not private because i want it to be shared with GameObject
	//so it can be initiated without putting it through the constructor
	//because when inherited from the subclass has that control
	//which i don't want
	
	private GameObject gameObject;	
	@Override
	public void onCollision(Collider col) {}
	@Override
	public void onRootChanged(GameObject parent) {}
		
	public GameObject getGameObject() {
		return gameObject;
	}
	public Scene getScene() {
		return getGameObject().getScene();
	}
	public Game getGame() {
		return getGameObject().getGame();
	}
	public Window getWindow() {
		return getGameObject().getWindow();
	}
	public Input getInput() {
		return getGameObject().getInput();
	}
	
	public void setGameObjectAttachedTo(GameObject gameObject) {
		if(this.gameObject != null)
			this.gameObject.removeComponent(this);
		
		this.gameObject = gameObject;
	}
	
	
		
}
