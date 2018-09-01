package GameEngine;

import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

/*
 * An abstract class to extend and create 
 * custom scenes that the game can host
 */

public abstract class Scene implements Renderable, Updateable{
	//Every scene has a camera, and a collider space
	private Camera camera;
	private ColliderSpace colliderSpace;
	
	private Game game;
	
	public Scene(Game game) {
		this.game = game;
		camera = new Camera(this, null);
		colliderSpace = new ColliderSpace();
	}
	
	@Override
	public void render() {
		camera.render();
	}
	@Override
	public void update() {
		//Update the camera first
		camera.update();
		//Check for collisions and call collision events
		colliderSpace.testCollision();
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Game getGame() {
		return game;
	}

	public ColliderSpace getColliderSpace() {
		return colliderSpace;
	}
		
}
