package GameEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;


//There can only ever be 1 window

public class Window extends AppGameContainer {

	private static Window window; 
	private Game game;
	
	
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
		return window.game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public static float getPixelToUnit(Scene scene) {
		return scene.getCamera().getOrthographicSize()/ window.getWidth();
	}
	public static float getUnitToPixels(Scene scene) {
		return window.getWidth()/scene.getCamera().getOrthographicSize();
	}

	public static Window getWindow() {
		return window;
	}
}
