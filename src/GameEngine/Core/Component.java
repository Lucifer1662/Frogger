package GameEngine.Core;
import org.newdawn.slick.Input;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;

public abstract class Component {
	//gameObject is not private because i want it to be shared with GameObject
	//so it can be initiated without putting it through the constructor
	//because when inherited from the subclass has that control
	//which i don't want
	
	private GameObject gameObject;	
	
	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
		gameObject.AddComponent(this);
	}
	
		
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
	
		
}
