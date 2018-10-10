package GameEngine.Core;

import GameEngine.Game;
import GameEngine.Scene;

/**
 * The base abstract Component to be extended and added to GameObject
 * 
 * @author Luke Hawkins
 *
 */
public abstract class Component {
	private final GameObject gameObject;

	/**
	 * Constructs a component with the GameObject as its parent
	 * 
	 * @param gameObject The GameObject to be attached to
	 */
	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
		gameObject.addComponent(this);
	}

	/**
	 * Gets the GameObject attached to
	 * 
	 * @return The GameObject attached to
	 */
	public final GameObject getGameObject() {
		return gameObject;
	}

	/**
	 * Gets the scene the component is in
	 * 
	 * @return The scene the component is in
	 */
	public final Scene getScene() {
		return getGameObject().getScene();
	}

	/**
	 * Gets the game the component is in
	 * 
	 * @return The Game the component is in
	 */
	public final Game getGame() {
		return getGameObject().getGame();
	}

}
