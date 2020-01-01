package FroggerGame.Tiles;

import GameEngine.Scene;
import GameEngine.Components.BoundingBox;

/**
 * A tile of water
 * 
 * @author lhawk
 *
 */
public class Water extends Tile {
	private static final String FILE_PATH = "assets/water.png";
	private static final float TILE_SIZE = 0.5f;

	/**
	 * Constructs a tile of water in a scene
	 * 
	 * @param scene The scene added to
	 */
	public Water(Scene scene) {
		super(scene, FILE_PATH);
		new BoundingBox(this, -TILE_SIZE, -TILE_SIZE, 2 * TILE_SIZE,
				2 * TILE_SIZE);
	}
}
