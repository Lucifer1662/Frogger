package FroggerGame.Obstacles;

import GameEngine.Scene;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/**
 * The base of all obstacle, that has a bounding box and a sprite
 * 
 * @author Luke Hawkins
 *
 */
public abstract class Obstactle extends GameObject {

	private static final int SPRITE_INDEX = 1;

	/**
	 * Constructs an obstacle
	 * 
	 * @param scene         The scene in
	 * @param width         The width
	 * @param height        The height
	 * @param imageLocation The image location
	 */
	public Obstactle(Scene scene, float width, float height,
			String imageLocation) {
		super(scene);
		new BoundingBox(this, -width / 2, -height / 2, width, height);
		new Sprite(this, imageLocation, -width / 2, -height / 2, width, height);
	}

	public abstract boolean isSafe();

	public abstract boolean isSolid();

	/**
	 * Gets the sprite used by the obstacle
	 * 
	 * @return The sprite used by the obstacle
	 */
	public Sprite getSprite() {
		return (Sprite) getComponent(SPRITE_INDEX);
	}
}
