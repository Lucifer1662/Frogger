package GameAndScene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Frog.Frog;
import FroggerGame.Frog.FrogLifeDisplayer;
import FroggerGame.Managers.ExtraLifeSpawner;
import FroggerGame.Managers.FrogEndManager;
import FroggerGame.Obstacles.Concrete.Bike;
import FroggerGame.Obstacles.Concrete.BullDozer;
import FroggerGame.Obstacles.Concrete.Bus;
import FroggerGame.Obstacles.Concrete.Log;
import FroggerGame.Obstacles.Concrete.LongLog;
import FroggerGame.Obstacles.Concrete.RaceCar;
import FroggerGame.Obstacles.Concrete.Turtle;
import FroggerGame.Tiles.Grass;
import FroggerGame.Tiles.Tree;
import FroggerGame.Tiles.Water;
import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Core.GameObject;

/**
 * A class that creates frogger scenes from files and hosts them
 * 
 * @author Luke Hawkins
 *
 */
public class FroggerScene extends Scene {
	private static final float TILE_SIZE = 48, SCREEN_WIDTH_IN_UNITS = 21,
			FROGEND_HEIGHT = 15;
	private static int FILE_POS_WIDTH = 1008, FILE_POS_HEIGHT = 768;
	private static final String PRE_LEVEL_PATH = "assets/levels/",
			POST_LEVEL_PATH = ".lvl";

	private int currentLevel;
	// 0 x is equivalent to pixel 512
	// -1 y is equivalent to pixel 720
	private static final int MAX_LEVEL = 1;

	/**
	 * Constructs a frogger scene in a game, with a specific level
	 * 
	 * @param game  The game to be run in
	 * @param level The level to be loaded
	 */
	public FroggerScene(Game game, int level) {
		super(game);
		currentLevel = level;

		// set the camera dimensions and position
		getCamera().SetOrthographicSize(SCREEN_WIDTH_IN_UNITS);

		float aspect = (float) Window.getWindow().getWidth()
				/ Window.getWindow().getHeight();
		getCamera().SetPoistion(0,
				(float) (getCamera().getOrthographicSize() / 2.0 / aspect) + 1);

		LoadScene(PRE_LEVEL_PATH + level + POST_LEVEL_PATH);
		FrogEndManager fm = new FrogEndManager(this);
		fm.getTransform().setPosition(0, FROGEND_HEIGHT);
	}

	private void LoadScene(String filePath) {
		Scanner scanner = null;
		ArrayList<Log> logs = new ArrayList<>();
		try {
			File file = new File(filePath);
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				GameObject gameObject = addGameObjectToScene(line);
				if (gameObject instanceof Log)
					logs.add((Log) gameObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}

		Frog frog = new Frog(this);
		new FrogLifeDisplayer(this, frog);
		new ExtraLifeSpawner(this, logs);

	}

	private GameObject addGameObjectToScene(String line) {
		String splits[] = line.split(",");

		GameObject gameObject = null;
		if (splits.length == 3) {
			switch (splits[0]) {
			case "water":
				gameObject = new Water(this);
				break;
			case "grass":
				gameObject = new Grass(this);
				break;
			case "tree":
				gameObject = new Tree(this);
				break;
			}
		} else if (splits.length == 4) {
			boolean isLeftToRight = Boolean.parseBoolean(splits[3]);
			switch (splits[0]) {
			case "bus":
				gameObject = new Bus(this, isLeftToRight);
				break;
			case "log":
				gameObject = new Log(this, isLeftToRight);
				break;
			case "longLog":
				gameObject = new LongLog(this, isLeftToRight);
				break;
			case "racecar":
				gameObject = new RaceCar(this, isLeftToRight);
				break;
			case "turtle":
				gameObject = new Turtle(this, isLeftToRight);
				break;
			case "bulldozer":
				gameObject = new BullDozer(this, isLeftToRight);
				break;
			case "bike":
				gameObject = new Bike(this, isLeftToRight);
				break;
			}
		}

		float x = (Integer.parseInt(splits[1]) - FILE_POS_WIDTH / 2.0f)
				/ TILE_SIZE + 0.5f;
		float y = (FILE_POS_HEIGHT - (float) Integer.parseInt(splits[2]))
				/ TILE_SIZE;
		gameObject.getTransform().setPosition(x, y);
		return gameObject;
	}

	/**
	 * Sets the game to get the next level or if no next level, closes the
	 * window
	 */
	public void nextLevel() {
		currentLevel++;
		if (currentLevel <= MAX_LEVEL) {
			getGame()
					.setCurrentScene(new FroggerScene(getGame(), currentLevel));
		} else {
			Window.getWindow().exit();
		}
	}

}
