package GameEngine;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Scene{
	private WorldRoot worldRoot;
	
	Game game;
	
	public Scene(Game game) {
		this.game = game;
		worldRoot = new WorldRoot(this, null);
	}
	
	final void Init(Window container) throws SlickException {
		worldRoot.Init(container);			
		init(container);
	}

	final void Render(Window window, Graphics graphics) throws SlickException {
		worldRoot.Render(window, graphics);
		render(window, graphics);
	}

	final void Update(Window window) throws SlickException {
		worldRoot.Update(window);
		update(window);
	}
	
	public void init(Window container) throws SlickException {}
	public void render(Window window, Graphics graphics) throws SlickException {}
	public void update(Window window) throws SlickException {}
	
	
	
	public final GameObject Instantiate(float posx, float posy, float scalex, float scaley, float angleOfRotation) {
		return new GameObject(this, worldRoot, posx, posy, scalex, scaley, angleOfRotation);
	}
	
	public final GameObject Instantiate(Vector2f pos, Vector2f scale, float angleOfRotation) {
		return Instantiate(pos.x, pos.y,scale.x, scale.y, angleOfRotation);
	}
		
	public final GameObject Instantiate(float posx, float posy) {
		return Instantiate(posx, posy,1, 1, 0);
	}
	
	

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		game.RemoveScene(this);
		this.game = game;
	}
	
	

	
}
