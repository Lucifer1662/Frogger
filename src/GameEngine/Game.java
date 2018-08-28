package GameEngine;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	private ArrayList<Scene> scenes;
	private Scene currentScene;
	private float timeDelta;
	private static int MILLISECONDS_IN_A_SECOND = 1000;
	private Window window;
	
	public Game(String title, String scenesLocation) {
		super(title);
		PrepareScenesLoadedFromFile(scenesLocation);
	}
	
	public Game(String title) {
		super(title);
		scenes = new ArrayList<Scene>();
	}
	
	public void SetCurrentScene(int index) {
		if(index > 0 && index < scenes.size())
			currentScene = scenes.get(index);
	}
	public void SetCurrentScene(Scene scene) {
		currentScene = scene;
	}
	
	public void RemoveScene(Scene scene) {
		scenes.remove(scene);
	}
	
	private void PrepareScenesLoadedFromFile(String filePah) {
		
	}
	
	@Override
	public final void render(GameContainer container, Graphics graphics) throws SlickException {
		currentScene.render();
		onDraw();
	}
	
	@Override
	public final void update(GameContainer container, int timeDeltaInMilliSeconds) throws SlickException {
		currentScene.update();
		timeDelta = timeDeltaInMilliSeconds * MILLISECONDS_IN_A_SECOND;
		onUpdate();
	}
	
	@Override
	public final void init(GameContainer arg0) throws SlickException {
		onStart();
	}

	
	public void onDraw() throws SlickException {}
	public void onUpdate() throws SlickException {}
	public void onStart() throws SlickException {};
	
	public float getTimeDelta() {
		return timeDelta;
	}
	
	public Window getWidow() {
		return window;
	}
	
	public void setWidow(Window window) {
		if(this.window != null)
			this.window.setGame(null);
		this.window = window;
	}

	


}
