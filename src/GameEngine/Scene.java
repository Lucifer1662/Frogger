package GameEngine;

import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

public abstract class Scene implements Renderable, Updateable{
	private Camera camera;
	private ColliderSpace colliderSpace;
	
	Game game;
	
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
		camera.update();
		colliderSpace.testCollision();
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		game.removeScene(this);
		this.game = game;
	}

	public ColliderSpace getColliderSpace() {
		return colliderSpace;
	}
		
}
