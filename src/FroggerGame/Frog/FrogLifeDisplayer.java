package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Scene;
import GameEngine.Components.Sprite;
import GameEngine.Core.UserInterfaceObject;

/**
 * Displays the frogs lives
 * 
 * @author Luke Hawkins
 *
 */
public class FrogLifeDisplayer extends UserInterfaceObject {

	Frog frogRef;
	int numOfFrogLifeSprites;

	/**
	 * Constructs a frog displayer
	 * 
	 * @param scene   The scene in
	 * @param frogRef The frog to observe
	 */
	public FrogLifeDisplayer(Scene scene, Frog frogRef) {
		super(scene);

		Vector2f pos = new Vector2f();
		pos.x = -getScene().getCamera().getOrthographicSize() / 2.0f;
		pos.y = (int) getScene().getCamera().getVerticalOrthoSize() / 2;
		getTransform().setPosition(pos);

		this.frogRef = frogRef;
		numOfFrogLifeSprites = frogRef.getNumberOfLives();
		for (int i = 0; i < numOfFrogLifeSprites; i++) {
			Sprite sprite = new Sprite(this, Frog.FROG_IMAGE_LOCATION);
			sprite.setX(i);
		}
	}

	@Override
	public void update() {
		while (numOfFrogLifeSprites < frogRef.getNumberOfLives())
			new Sprite(this, Frog.FROG_IMAGE_LOCATION).setX(numOfFrogLifeSprites++);

		while (numOfFrogLifeSprites > frogRef.getNumberOfLives())
			this.removeComponent(--numOfFrogLifeSprites);

	}

}
