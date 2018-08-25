package FroggerGame;

import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Window;

public class FroggerGame extends Game {
	
	public FroggerGame() {
		super("Frogger");
	}
	
	@Override
	public void Init() {
		MainLevelScene mainLevel = new MainLevelScene(this);
		Frog frog = new Frog();
		frog.setScene(mainLevel);
		frog.getTransform().setPosition(0,-4.5f);
		
		
		Bus bus = new Bus();
		bus.setScene(mainLevel);
		
		SetCurrentScene(mainLevel);
	}
	
	

	public static void main(String[] args) {
		try {			
			Window window = new Window(new FroggerGame());
			window.setDisplayMode(600, 600, false);
			window.setVSync(true);
			window.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
