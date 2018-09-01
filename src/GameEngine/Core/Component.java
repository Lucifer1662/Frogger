package GameEngine.Core;

import GameEngine.Game;
import GameEngine.Scene;

public abstract class Component {
	private final GameObject gameObject;	
	
	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
		gameObject.AddComponent(this);
	}
		
	public final GameObject getGameObject() {
		return gameObject;
	}
	public final Scene getScene() {
		return getGameObject().getScene();
	}
	public final Game getGame() {
		return getGameObject().getGame();
	}	
		
}
