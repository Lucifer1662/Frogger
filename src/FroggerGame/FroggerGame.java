package FroggerGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Window;

public class FroggerGame extends Game {
	private static int SCREEN_WIDTH = 1024, SCREEN_HEIGHT = 768;
	public FroggerGame() {
		super("Frogger");
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		MainLevel mainLevel = new MainLevel(this);		
		setCurrentScene(mainLevel);
	}
	

	public static void main(String[] args) {
		//Create Frogger Game and window
		try {			
			Window window = Window.CreateWindow(new FroggerGame());
			window.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			window.setVSync(true);
			window.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
