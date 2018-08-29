package GameEngine;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;
import GameEngine.GameObjects.Camera;

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

	
	
	public final GameObject Instantiate(float posx, float posy, float scalex, float scaley, float angleOfRotation) {
		return new GameObject(this, camera, posx, posy, scalex, scaley, angleOfRotation);
	}
	
	public final GameObject Instantiate(Vector2f pos, Vector2f scale, float angleOfRotation) {
		return Instantiate(pos.x, pos.y,scale.x, scale.y, angleOfRotation);
	}
		
	public final GameObject Instantiate(float posx, float posy) {
		return Instantiate(posx, posy,1, 1, 0);
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
