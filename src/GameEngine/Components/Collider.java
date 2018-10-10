package GameEngine.Components;

import java.util.HashSet;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;

/*
 * Used to represent an abstract collider
 *  that can collide with other colliders
 */
public abstract class Collider extends Component {
	HashSet<Collider> collidingColliders = new HashSet<Collider>();

	public Collider(GameObject gameObject) {
		super(gameObject);
		if (GameObject.canGetScene(getGameObject()))
			getScene().getColliderSpace().add(this);
	}

	/*
	 * Test collision with another collider
	 */
	public abstract boolean testCollision(Collider col);

	public CollisionType collision(Collider col) {
		boolean colliding = testCollision(col);
		CollisionType collisionType;
		if (colliding) {
			if (collidingColliders.add(col))
				collisionType = CollisionType.Enter;
			else
				collisionType = CollisionType.Already;

			col.collidingColliders.add(this);
		} else {
			if (collidingColliders.remove(col))
				collisionType = CollisionType.Exit;
			else
				collisionType = CollisionType.NoCollision;

			col.collidingColliders.remove(this);
		}
		return collisionType;
	}

	public enum CollisionType {
		Enter, Already, Exit, NoCollision;
	}
}
