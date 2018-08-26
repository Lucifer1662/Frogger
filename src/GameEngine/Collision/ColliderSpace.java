package GameEngine.Collision;

import java.util.ArrayList;
import java.util.List;

public class ColliderSpace{
	private List<Collider> colliders;
	
	public ColliderSpace() {
		colliders = new ArrayList<Collider>();
	}
	
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
	
	void remove(Collider col) {
		colliders.remove(col);
	}
	void add(Collider col) {
		colliders.add(col);
	}
}
