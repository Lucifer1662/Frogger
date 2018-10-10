package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Interfaces.Pushable;
import FroggerGame.Obstacles.Obstactle;
import FroggerGame.Obstacles.RideableObstacle;
import FroggerGame.Obstacles.Concrete.Log;
import FroggerGame.Tiles.Water;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnCollisionable;
import GameEngine.CoreInterfaces.Updateable;

/**
 * Represents a frog behavior component that doesn't know how to move
 */
public abstract class FrogComponent extends Component implements Updateable, ControlableMovement, OnCollisionable {

	private Vector2f deltaMoved;
	private boolean collidedWithWater = false;

	private static final float MIN_Y = -1;

	/**
	 * Constructs a frog component
	 * 
	 * @param gameObject The parent
	 */
	public FrogComponent(GameObject gameObject) {
		super(gameObject);
	}

	/**
	 * Updates the frog component
	 */
	@Override
	public void update() {

		if (collidedWithWater && !(getGameObject().getParent() instanceof RideableObstacle))
			((Frog) getGameObject()).removeLife();

		// get the position
		Vector2f pos = getGameObject().getTransform().getWorldPosition();
		float bounding = getScene().getCamera().getOrthographicSize() / 2;
		if (pos.x < -bounding || pos.x > bounding || pos.y < MIN_Y)
			((Frog) getGameObject()).removeLife();

		pos = getGameObject().getTransform().getPosition();

		Vector2f dir = GetMovenent();
		pos.add(dir);
		// check we still in bounds and then apply position
		if (pos.x > -bounding && pos.x < bounding && pos.y >= MIN_Y) {
			// add the movement
			deltaMoved = dir;
			// update position
			getGameObject().getTransform().setPosition(pos);
		} else
			deltaMoved = new Vector2f(0, 0);

	}

	private void walkingThroughtSolid(GameObject gameObject) {
		if (gameObject instanceof Obstactle && ((Obstactle) gameObject).isSolid()) {
			getGameObject().getTransform().setPosition(getGameObject().getTransform().getPosition().sub(deltaMoved));
		}
	}

	@Override
	public void onCollisionEnter(Collider collider) {
		// i am in a solid object, go back to old pos
		GameObject gameObject = collider.getGameObject();
		walkingThroughtSolid(gameObject);
	}

	@Override
	public void onColliding(Collider collider) {

		GameObject gameObject = collider.getGameObject();
		if (gameObject instanceof Water && !(getGameObject().getParent() instanceof RideableObstacle)) {
			((Frog) getGameObject()).removeLife();
		}
		walkingThroughtSolid(gameObject);
	}

}
