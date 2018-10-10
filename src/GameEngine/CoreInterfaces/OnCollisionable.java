package GameEngine.CoreInterfaces;

import GameEngine.Components.Collider;

/**
 * An interface for a class that wants to know when a collision occurs
 * 
 * @author Luke Hawkins
 *
 */
public interface OnCollisionable {

	default public void onCollisionEnter(Collider col) {
	}

	default public void onColliding(Collider col) {
	}

	default public void onCollisionExit(Collider col) {
	}

}
