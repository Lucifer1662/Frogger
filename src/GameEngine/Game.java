package GameEngine;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/*
 * An abstract class to create a game to host a scene in a window
 */

public abstract class Game extends BasicGame{
	private Scene currentScene;
	private float timeDelta;
	private static int MILLISECONDS_IN_A_SECOND = 1000;
	
	public Game(String title) {
		super(title);
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		//render the current scene
		currentScene.render();
	}
	
	@Override
	public void update(GameContainer container, int timeDeltaInMilliSeconds) throws SlickException {
		//update current scene and time delta
		currentScene.update();
		timeDelta = timeDeltaInMilliSeconds/(float)MILLISECONDS_IN_A_SECOND;
	}
	
	public void setCurrentScene(Scene scene) {
		currentScene = scene;		
	}
	
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	public float getTimeDelta() {
		return timeDelta;
	}

}
