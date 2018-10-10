package FroggerGame.Tiles;

import GameEngine.Scene;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/**
 * A generic tile
 * 
 * @author lhawk
 *
 */
public class Tile extends GameObject {

	/**
	 * Constructs a tile, and renders the image from the file path
	 * 
	 * @param scene    The scene to be added to
	 * @param filePath Where the image will be loaded from
	 */
	public Tile(Scene scene, String filePath) {
		super(scene);
		new Sprite(this, filePath, true);
	}

}
