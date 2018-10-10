package FroggerGame.Tiles;

import GameEngine.Scene;

/**
 * The grass tile
 * 
 * @author Luke Hawkins
 *
 */
public class Grass extends Tile {
	private static final String FILE_PATH = "assets/grass.png";

	/**
	 * Constructs a grass tile
	 * 
	 * @param scene The scene to be added to
	 */
	public Grass(Scene scene) {
		super(scene, FILE_PATH);
	}
}
