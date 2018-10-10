package GameEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;

//There can only ever be 1 window

/**
 * A singleton class to host a window to host a game in
 * 
 * @author Luke Hawkins
 */
public class Window extends AppGameContainer {

	private static Window window;
	private Game game;

	Window(Game game) throws SlickException {
		super(game);
		this.game = game;
		window = this;
	}

	/**
	 * Creates a window or returns the current singleton
	 * 
	 * @param game The game to be created or set
	 * @return A window holding the game
	 * @throws SlickException
	 */
	public static Window CreateWindow(Game game) throws SlickException {
		// Create singleton
		if (window == null)
			window = new Window(game);
		else
			window.game = game;
		return window;
	}

	/**
	 * Gets the game that the window holds
	 * 
	 * @return The game the window holds
	 */
	public Game getGame() {
		return window.game;
	}

	/**
	 * Sets the windows game to this game
	 * 
	 * @param game The game to be set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Gets the ratio between a pixel to a unit
	 * 
	 * @param scene The scene to be examined
	 * @return Pixels in a unit
	 */
	public static float getPixelToUnit(Scene scene) {
		return scene.getCamera().getOrthographicSize() / window.getWidth();
	}

	/**
	 * Gets the ratio between a unit to a pixel
	 * 
	 * @param scene The scene to be examined
	 * @return Unit in pixels
	 */
	public static float getUnitToPixels(Scene scene) {
		return window.getWidth() / scene.getCamera().getOrthographicSize();
	}

	/**
	 * Gets the current singleton window
	 * 
	 * @return The current singleton window
	 */
	public static Window getWindow() {
		return window;
	}
}
