/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * Edited by Luke Hawkins on 26/8/2018
 */
package GameEngine;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Collision.Collider;

public class BoundingBox extends Collider{
	private float left, top, width, height;

	public BoundingBox(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		setX(x);
		setY(y);
	}

	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/*
	 * Sets the x and y position at the centre of the bounding box.
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
	
	public void getWorldPos(Vector2f min, Vector2f max) {
		min.x = this.left;
		max.x = getRight();
		max.y = this.top;
		min.y = getBottom();
		getGameObject().getTransform().transform(min);
		getGameObject().getTransform().transform(max);
	}
	
	public boolean intersects(BoundingBox other) {
		Vector2f min1 = new Vector2f(), min2 = new Vector2f(),
				max1 =  new Vector2f(), max2 = new Vector2f();
		
		getWorldPos(min1, max1);
		other.getWorldPos(min2, max2);
		
		if( max1.x < min2.x || min1.x > max2.x ) return false;
	    if( max1.y < min2.y || min1.y > max2.y ) return false;
		return true;
		
	}

	@Override
	public boolean testCollision(Collider col) {
		if(col == null)
			return false;
		
		if(col instanceof BoundingBox) {
			return intersects((BoundingBox)col);
		}
		
		return false;
	}
}