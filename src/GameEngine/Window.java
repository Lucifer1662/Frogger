package GameEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;

public class Window extends AppGameContainer {
	
	private Game game;
	private static Window window; 
	
	Window(Game game) throws SlickException {
		super(game);
		this.game = game;
		window = this;
	}
	
	public static Window CreateWindow(Game game) throws SlickException {
		if(window == null)
			window = new Window(game);
		return window;
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public float getPixelToUnit(Scene scene) {
		return scene.getCamera().getOrthographicSize()/getWidth();
	}
	public float getUnitToPixels(Scene scene) {
		return getWidth()/scene.getCamera().getOrthographicSize();
	}

	public static Window getWindow() {
		return window;
	}
}
