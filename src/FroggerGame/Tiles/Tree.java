package FroggerGame.Tiles;

import FroggerGame.Obstacles.Obstactle;
import GameEngine.Scene;

/**
 * A tile of a tree
 * 
 * @author Luke Hawkins
 *
 */
public class Tree extends Obstactle {
	private final static String FILE_PATH = "assets/tree.png";
	private final static float WIDTH = 1, HEIGHT = 1;

	/**
	 * Constructs a tree in a scene
	 * 
	 * @param scene
	 */
	public Tree(Scene scene) {
		super(scene, WIDTH, HEIGHT, FILE_PATH);
	}

	@Override
	public boolean isSafe() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

}
