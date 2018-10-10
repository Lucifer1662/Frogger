package GameEngine;

import java.util.ArrayList;
import java.util.List;

import GameEngine.Components.Collider;

/**
 * A space to host the colliders and check if they collide and invoke the
 * onCollision event
 * 
 * @author Luke Hawkins
 */

public class ColliderSpace {
	private List<Collider> colliders = new ArrayList<Collider>();

	/**
	 * Tests all of the colliders against each other and hands out the events
	 */
	@SuppressWarnings("incomplete-switch")
	public void testCollision() {
		Collider col1, col2;
		for (int i = 0; i < colliders.size(); i++) {
			for (int j = i + 1; j < colliders.size(); j++) {
				col1 = colliders.get(i);
				col2 = colliders.get(j);

				switch (col1.collision(col2)) {
				case Already:
					col1.getGameObject().onColliding(col2);
					col2.getGameObject().onColliding(col1);
					break;
				case Enter:
					col1.getGameObject().onCollisionEnter(col2);
					col2.getGameObject().onCollisionEnter(col1);
					break;
				case Exit:
					col1.getGameObject().onCollisionExit(col2);
					col2.getGameObject().onCollisionExit(col1);
					break;
				}
			}
		}
	}

	/**
	 * Removes a collider to the collider space
	 * 
	 * @param col The collider to be removed to the colliderspace
	 */
	public void remove(Collider col) {
		colliders.remove(col);
	}

	/**
	 * Adds a collider to the collider space
	 * 
	 * @param col The collider to be addded to the colliderspace
	 */
	public void add(Collider col) {
		colliders.add(col);
	}
}
