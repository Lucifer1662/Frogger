package GameEngine;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * An abstract class to create a game to host a scene in a window
 * 
 * @author Luke Hawkins
 */

public abstract class Game extends BasicGame {
	private Scene currentScene;
	private float timeDelta;
	private static int MILLISECONDS_IN_A_SECOND = 1000;

	/**
	 * Constructs a game with a title
	 * 
	 * @param title The title of the game
	 */
	public Game(String title) {
		super(title);
	}

	/**
	 * Renders the game
	 * 
	 * @param container The gamecontainer the game is in
	 * @param graphics  The graphics of the window
	 */
	@Override
	public void render(GameContainer container, Graphics graphics)
			throws SlickException {
		// render the current scene
		currentScene.render();
	}

	/**
	 * updates the game
	 * 
	 * @param container               The game container the game is in
	 * @param timeDeltaInMilliSeconds The change in time in milli seconds
	 */
	@Override
	public void update(GameContainer container, int timeDeltaInMilliSeconds)
			throws SlickException {
		// update current scene and time delta
		currentScene.update();
		timeDelta = timeDeltaInMilliSeconds / (float) MILLISECONDS_IN_A_SECOND;
	}

	/**
	 * Sets the current scene
	 * 
	 * @param scene The scene to be set to current
	 */
	public void setCurrentScene(Scene scene) {
		currentScene = scene;
	}

	/**
	 * Gets the current scene
	 * 
	 * @return The current scene
	 */
	public Scene getCurrentScene() {
		return currentScene;
	}

	/**
	 * Gets the change in time in seconds
	 * 
	 * @return The change in time in seconds
	 */
	public float getTimeDelta() {
		return timeDelta;
	}

}
