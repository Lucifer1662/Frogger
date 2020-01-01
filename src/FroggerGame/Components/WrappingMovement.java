package FroggerGame.Components;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/**
 * A component that causes the gameobject to wrap around the screen
 * 
 * @author Luke Hawkins
 *
 */
public class WrappingMovement extends Component implements Updateable {
	private float speed;
	private float lengthUntilOffScreen;

	/**
	 * Construct a wrapping movement on a parent, at a speed, and distance until
	 * off the screen
	 * 
	 * @param gameObject           The parent
	 * @param speed                The speed
	 * @param lengthUntilOffScreen The distance from 0 until it wraps around
	 */
	public WrappingMovement(GameObject gameObject, float speed,
			float lengthUntilOffScreen) {
		super(gameObject);
		this.speed = speed;
		this.lengthUntilOffScreen = lengthUntilOffScreen;
	}

	/**
	 * Updates the wrapping movement
	 */
	@Override
	public void update() {
		// get the position
		Vector2f pos = getGameObject().getTransform().getPosition();
		// add the speed to it * delta
		pos.x += speed * getGame().getTimeDelta();

		float maxDistance = getScene().getCamera().getOrthographicSize() / 2.0f
				+ lengthUntilOffScreen;

		// check if obstacle should go to other side of the screens
		if (speed > 0 && pos.x > maxDistance)
			pos.x = -maxDistance;
		else if (speed < 0 && pos.x < -maxDistance)
			pos.x = maxDistance;

		// update the position
		getGameObject().getTransform().setPosition(pos);
	}

	/**
	 * Gets the speed of the wrapping movement
	 * 
	 * @return The speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the wrapping movement
	 * 
	 * @param speed The speed to be set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Gets the length until the object will be wrapping
	 * 
	 * @return The length until the object will be wrapped
	 */
	public float getlengthUntilOffScreen() {
		return lengthUntilOffScreen;
	}

	/**
	 * Sets the length until the object will be wrapped
	 * 
	 * @param lengthUntilOffScreen The length until the object will be wrapped
	 */
	public void setlengthUntilOffScreen(float lengthUntilOffScreen) {
		if (lengthUntilOffScreen > 0)
			this.lengthUntilOffScreen = lengthUntilOffScreen;
	}
}
