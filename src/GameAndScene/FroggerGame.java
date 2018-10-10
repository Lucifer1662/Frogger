package GameAndScene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.Window;

/**
 * Hosts the game Frogger
 * 
 * @author Luke Hawkins
 *
 */
public class FroggerGame extends Game {
	public static int SCREEN_WIDTH = 1024, SCREEN_HEIGHT = 768;

	/**
	 * Constructs a frogger game
	 */
	public FroggerGame() {
		super("Frogger");
	}

	/**
	 * Init's the game in a game container
	 * 
	 * @param container The container of this game
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
		// MainLevel mainLevel = new MainLevel(this);
		// setCurrentScene(mainLevel);
		FroggerScene scene = new FroggerScene(this, 0);
		setCurrentScene(scene);
	}

	public static void main(String[] args) {
		// Create Frogger Game and window
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
