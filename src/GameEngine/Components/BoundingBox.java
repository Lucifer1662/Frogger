/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * Edited by Luke Hawkins on 26/8/2018
 */
package GameEngine.Components;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.GameObject;

/**
 * An extension of collider, that represents a bounding box and uses the game
 * objects world position and scale
 * 
 * @author Luke Hawkins
 */
public class BoundingBox extends Collider {
	private float left, bottom, width, height;

	/**
	 * Constructs a bounding box attached to a gameObject, offset (x,y), size
	 * (width, heigh)
	 * 
	 * @param gameObject The parent
	 * @param x          The offset in the x direction
	 * @param y          The offset in the y direction
	 * @param width      The width of the bounding box
	 * @param height     The height of the bounding box
	 */
	public BoundingBox(GameObject gameObject, float x, float y, float width, float height) {
		super(gameObject);
		this.width = width;
		this.height = height;
		left = x;
		bottom = y;
	}

	/**
	 * Sets the x offset
	 * 
	 * @param x The offset
	 */
	public void setX(float x) {
		left = x;// - width / 2;
	}

	/**
	 * Sets the y offset
	 * 
	 * @param y The offset
	 */
	public void setY(float y) {
		bottom = y;// - height / 2;
	}

	/**
	 * Sets the width
	 * 
	 * @param w The width
	 */
	public void setWidth(float w) {
		width = w;
	}

	/**
	 * Sets the height
	 * 
	 * @param h The height
	 */
	public void setHeight(float h) {
		height = h;
	}

	/**
	 * Gets the left
	 * 
	 * @return The left position
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * Gets the top
	 * 
	 * @return The top position
	 */
	public float getTop() {
		return bottom + height;
	}

	/**
	 * Gets the right
	 * 
	 * @return The right position
	 */
	public float getRight() {
		return left + width;
	}

	/**
	 * Gets the bottom
	 * 
	 * @return The bottom position
	 */
	public float getBottom() {
		return bottom;
	}

	/**
	 * Gets the width
	 * 
	 * @return The width of the bounding box
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Gets the height
	 * 
	 * @return The height of the bounding box
	 */
	public float getHeight() {
		return height;
	}

	/*
	 * gets the world position of the two vectors
	 */
	private void getWorldPos(Vector2f min, Vector2f max) {
		min.x = getLeft();
		max.x = getRight();
		max.y = getTop();
		min.y = getBottom();
		Transform trans = getGameObject().getTransform();

		min.x = trans.getWorldScale().x * min.x;
		min.y = trans.getWorldScale().y * min.y;
		max.x = trans.getWorldScale().x * max.x;
		max.y = trans.getWorldScale().y * max.y;

		min = min.add(trans.getWorldPosition());
		max = max.add(trans.getWorldPosition());
	}

	/**
	 * Test intersection with another bounding box
	 * 
	 * @param other The other bounding box
	 * @return Whether there is an intersection
	 */
	public boolean intersects(BoundingBox other) {
		Vector2f min1 = new Vector2f(), min2 = new Vector2f(), max1 = new Vector2f(), max2 = new Vector2f();

		getWorldPos(min1, max1);
		other.getWorldPos(min2, max2);

		// test AABB collision
		if (max1.x <= min2.x || min1.x >= max2.x)
			return false;
		if (max1.y <= min2.y || min1.y >= max2.y)
			return false;
		return true;
	}

	/**
	 * Tests intersection with generic Collider
	 * 
	 * @param col The collider to be tested with
	 */
	@Override
	public boolean testCollision(Collider col) {
		// checks colliders isn't null
		if (col == null)
			return false;

		// checks if is boundingBox if so then checks
		if (col instanceof BoundingBox) {
			return intersects((BoundingBox) col);
		}

		// if the collider is not recognized, then return false
		return false;
	}
}
