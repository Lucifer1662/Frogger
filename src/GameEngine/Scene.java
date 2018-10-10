package GameEngine;

import java.time.chrono.IsoEra;

import GameEngine.Core.GameObject;
import GameEngine.Core.UserInterfaceObject;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

/**
 * An abstract class to extend and create custom scenes that the game can host
 * 
 * @author Luke Hawkins
 */

public abstract class Scene implements Renderable, Updateable {
	// Every scene has a camera, and a collider space
	private Camera camera;
	private ColliderSpace colliderSpace;

	private Game game;

	/**
	 * Constructs a scene within a game
	 * 
	 * @param game The game for the scene to be created in
	 */
	public Scene(Game game) {
		this.game = game;
		camera = new Camera(this, null);
		colliderSpace = new ColliderSpace();
	}

	/**
	 * Renders the scene
	 */
	@Override
	public void render() {
		camera.render();
	}

	/**
	 * Updates the scene
	 */
	@Override
	public void update() {

		// Update the camera first
		camera.update();
		// Check for collisions and call collision events
		colliderSpace.testCollision();

	}

	/**
	 * Gets the camera of the scene
	 * 
	 * @return The scene's camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * Gets the game the scene is in
	 * 
	 * @return The scene the game is in
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Gets the colliderspace of the scene
	 * 
	 * @return The colliderspace of the scene
	 */
	public ColliderSpace getColliderSpace() {
		return colliderSpace;
	}

}
