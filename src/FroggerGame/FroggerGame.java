package FroggerGame;

import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Window;

public class FroggerGame extends Game {
	
	public FroggerGame() {
		super("Frogger");
	}
	
	@Override
	public void onStart() {
		MainLevelScene mainLevel = new MainLevelScene(this);
		mainLevel.getCamera().SetOrthographicSize(getWidow(), 21);
		mainLevel.getCamera().SetPoistion(0, 21/2.0f);
		
		/*HighWayRow highWay = new HighWayRow();
		highWay.setScene(mainLevel);
		highWay.getTransform().setPosition(0, 2);
		FroggerRow row = new FroggerRow("assets/grass.png");
		row.setScene(mainLevel);
		*/
		Frog frog = new Frog();
		frog.setScene(mainLevel);
		frog.getTransform().setPosition(0,0);
		
		
		Bus bus = new Bus();
		bus.setScene(mainLevel);
		
		SetCurrentScene(mainLevel);
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
