package GameEngine;

import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Collision.ColliderSpace;

public abstract class Scene{
	private WorldRoot worldRoot;
	private ColliderSpace colliderSpace;
	
	Game game;
	
	public Scene(Game game) {
		this.game = game;
		worldRoot = new WorldRoot(this, null);
		colliderSpace = new ColliderSpace();
	}
	

	final void _render() throws SlickException {
		worldRoot._render();
		render();
	}

	final void _update() throws SlickException {
		worldRoot._update();
		colliderSpace.testCollision();
		update();
	}
	
	public void init() throws SlickException {}
	public void render() throws SlickException {}
	public void update() throws SlickException {}
	
	
	
	public final GameObject Instantiate(float posx, float posy, float scalex, float scaley, float angleOfRotation) {
		return new GameObject(this, worldRoot, posx, posy, scalex, scaley, angleOfRotation);
	}
	
	public final GameObject Instantiate(Vector2f pos, Vector2f scale, float angleOfRotation) {
		return Instantiate(pos.x, pos.y,scale.x, scale.y, angleOfRotation);
	}
		
	public final GameObject Instantiate(float posx, float posy) {
		return Instantiate(posx, posy,1, 1, 0);
	}
	
	public WorldRoot getWorldRoot() {
		return worldRoot;
	}

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		game.RemoveScene(this);
		this.game = game;
	}

	public ColliderSpace getColliderSpace() {
		return colliderSpace;
	}
	
	

	
}
