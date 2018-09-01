package GameEngine;

import java.util.ArrayList;
import java.util.List;

import GameEngine.Components.Collider;

/*
 * A space to host the colliders and check if they collide
 * and invoke the onCollision event
 */

public class ColliderSpace{
	private List<Collider> colliders = new ArrayList<Collider>();
	
	public void testCollision() {
		Collider col1, col2;
		for(int i =0; i < colliders.size(); i++) {
			for(int j = i+1; j < colliders.size(); j++) {
				col1 = colliders.get(i);
				col2 = colliders.get(j);
					
				if(col1.testCollision((col2))) {
					col1.getGameObject().onCollision(col2);
					col2.getGameObject().onCollision(col1);
				}
			}
		}
	}
	
	public void remove(Collider col) {
		colliders.remove(col);
	}
	public void add(Collider col) {
		colliders.add(col);
	}
}
