package FroggerGame.Tiles;

import GameEngine.Scene;

/**
 * A tile of a tree
 * 
 * @author Luke Hawkins
 *
 */
public class Tree extends Tile {
	private final static String FILE_PATH = "assets/tree.png";

	/**
	 * Constructs a tree in a scene
	 * 
	 * @param scene
	 */
	public Tree(Scene scene) {
		super(scene, FILE_PATH);
	}

}
