package GameEngine;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import GameEngine.CoreInterfaces.Initializable;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

public class Game extends BasicGame implements Renderable, Updateable, Initializable {
	private ArrayList<Scene> scenes;
	private Scene currentScene;
	private float timeDelta;
	private static int MILLISECONDS_IN_A_SECOND = 1000;
	
	public Game(String title) {
		super(title);
		scenes = new ArrayList<Scene>();
	}
	
	public void setCurrentScene(int index) {
		if(index > 0 && index < scenes.size()) {
			currentScene = scenes.get(index);
		}
	}
	public void setCurrentScene(Scene scene) {
		if(!scenes.contains(scene))
			scenes.add(scene);
		
		currentScene = scene;		
	}
	public void addScene(Scene scene) {
		scenes.add(scene);
	}
	
	public void removeScene(Scene scene) {
		scenes.remove(scene);
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		currentScene.render();
		render();
	}
	
	@Override
	public void update(GameContainer container, int timeDeltaInMilliSeconds) throws SlickException {
		currentScene.update();
		timeDelta = timeDeltaInMilliSeconds/(float)MILLISECONDS_IN_A_SECOND;
		update();
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		init();
	}
	
	@Override
	public void render() {}
	@Override
	public void update() {}
	@Override
	public void init() {}
	
	
	public float getTimeDelta() {
		return timeDelta;
	}

	
	public Scene getCurrentScene() {
		return currentScene;
	}

	


}
