package FroggerGame.Components;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Components.Sprite;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/**
 * A component that disappears and reappears the a sprite
 * 
 * @author Luke Hawkins
 *
 */
public class DisappearReappear extends Component implements Updateable {
	private float visibleDuration, invisibleDuration, time;
	private boolean isVisible = true;
	private Sprite sprite;
	private static Random random = new Random();

	/**
	 * Constructs a Disappears Reappears
	 * 
	 * @param gameObject        The parent
	 * @param sprite            The sprite to control
	 * @param visibleDuration   The visible duration
	 * @param invisibleDuration The invisible duration
	 */
	public DisappearReappear(GameObject gameObject, Sprite sprite, float visibleDuration, float invisibleDuration) {
		super(gameObject);
		this.sprite = sprite;
		this.visibleDuration = visibleDuration;
		this.invisibleDuration = invisibleDuration;
		time += random.nextFloat() * visibleDuration;
	}

	@Override
	public void update() {
		time += getGame().getTimeDelta();
		if (time > (isVisible ? visibleDuration : invisibleDuration)) {
			time = 0;
			isVisible = !isVisible;
			sprite.setVisibility(isVisible);
		}
	}

	/**
	 * Gets the current visibility
	 * 
	 * @return The current visibility
	 */
	public boolean getVisiblity() {
		return isVisible;
	}

}
