/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * Edited by Luke Hawkins on 26/8/2018
 */
package GameEngine.Components;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.GameObject;

/*
 * An extension of collider, that represents a bounding box
 * and uses the game objects world position and scale
 */
public class BoundingBox extends Collider{
	private float left, top, width, height;

	public BoundingBox(GameObject gameObject, float x, float y, float width, float height) {
		super(gameObject);
		this.width = width;
		this.height = height;
		setX(x);
		setY(y);
	}

	public BoundingBox(BoundingBox bb) {
		super(bb.getGameObject());
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/*
	 * Sets the x and y position at the center of the bounding box.
	 */
	public void setX(float x) {
		left = x - width / 2;
	}
	public void setY(float y) {
		top = y - height / 2;
	}
	
	public void setWidth(float w) {
		width = w;
	}
	public void setHeight(float h) {
		height = h;
	}
	
	public float getLeft() {
		return left;
	}
	public float getTop() {
		return top;
	}
	public float getRight() {
		return left + width;
	}
	public float getBottom() {
		return top + height;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	/*
	 * gets the world position of the two vectors
	 */
	private void getWorldPos(Vector2f min, Vector2f max) {
		min.x = this.left;
		max.x = getRight();
		max.y = this.top;
		min.y = getBottom();
		Transform trans = getGameObject().getTransform();
		
		min.x = trans.getWorldScale().x * min.x;
		min.y = trans.getWorldScale().y * min.y;
		max.x = trans.getWorldScale().x * max.x;
		max.y = trans.getWorldScale().y * max.y;
		
		min = min.add(trans.getWorldPixelPosition());
		max = max.add(trans.getWorldPixelPosition());
	}
	
	public boolean intersects(BoundingBox other) {
		Vector2f min1 = new Vector2f(), min2 = new Vector2f(),
				max1 =  new Vector2f(), max2 = new Vector2f();
		
		getWorldPos(min1, max1);
		other.getWorldPos(min2, max2);
		
		//test AABB collision
		if( max1.x <= min2.x || min1.x >= max2.x ) return false;
	    if( max1.y <= min2.y || min1.y >= max2.y ) return false;
		return true;
	}

	@Override
	public boolean testCollision(Collider col) {
		//checks colliders isn't null
		if(col == null)
			return false;
		
		//checks if is boundingBox if so then checks
		if(col instanceof BoundingBox) {
			return intersects((BoundingBox)col);
		}
		
		//if the collider is not recognized, then return false
		return false;
	}
}
