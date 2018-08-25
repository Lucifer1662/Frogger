package GameEngine;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Scene{
	private WorldRoot worldRoot;
	
	Game game;
	
	public Scene(Game game) {
		this.game = game;
		worldRoot = new WorldRoot(this, null);
	}
	
	final void Init() throws SlickException {
		//worldRoot.Init();			
		init();
	}

	final void Render() throws SlickException {
		worldRoot.Render();
		render();
	}

	final void Update() throws SlickException {
		worldRoot.Update();
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
	
	

	
}
