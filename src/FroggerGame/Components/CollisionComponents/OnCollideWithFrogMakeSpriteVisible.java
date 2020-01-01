package FroggerGame.Components.CollisionComponents;

import FroggerGame.Frog.Frog;
import GameEngine.Components.Collider;
import GameEngine.Components.Sprite;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;

/**
 * A component that in collision with a frog sets a sprite invisible
 * 
 * @author lhawk
 *
 */
public class OnCollideWithFrogMakeSpriteVisible extends Component
		implements OnCollisionable {
	private Sprite sprite;

	/**
	 * Constructs a OnCollideWithFrogMakeSpriteVisible
	 * 
	 * @param gameObject The parent
	 * @param sprite     The sprite who's visiblity is the be set
	 */
	public OnCollideWithFrogMakeSpriteVisible(GameObject gameObject,
			Sprite sprite) {
		super(gameObject);
		this.sprite = sprite;
	}

	/**
	 * The on collision enter event
	 * 
	 * @param collider The collider collided with
	 */
	@Override
	public void onCollisionEnter(Collider collider) {
		if (collider.getGameObject() instanceof Frog) {
			sprite.setVisibility(true);
		}
	}

}
