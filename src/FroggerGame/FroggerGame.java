package FroggerGame;

import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Window;

public class FroggerGame extends Game {
	
	public FroggerGame() {
		super("Frogger");
	}
	
	@Override
	public void init() {
		MainLevel mainLevel = new MainLevel(this);		
		setCurrentScene(mainLevel);
	}
	
	

	public static void main(String[] args) {
		try {			
			Window window = new Window(new FroggerGame());
			window.setDisplayMode(800, 600, false);
			window.setVSync(true);
			window.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
