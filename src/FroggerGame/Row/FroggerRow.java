package FroggerGame.Row;
import java.util.concurrent.Callable;

import GameEngine.Scene;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/*
 * A row in frogger
 */
public abstract class FroggerRow extends GameObject{
	private static final float TILE_OFFSET = 0.5f;
	
	/*
	 * Create row with just tiles
	 */
	public FroggerRow(Scene scene, String tilePath, float rowLength){
		super(scene);
		for(int i = 0; i < rowLength; i++) {
			GameObject tile = new GameObject(scene,this);
			tile.getTransform().setPosition(i-rowLength/2.0f + TILE_OFFSET,0);
			new Sprite(tile, tilePath, true);
		}
	}
	
	/*
	 * Add obstacles to the row
	 */
	<T extends GameObject>
	void CreateObstacles(float offset, float seperation, float stopAt, Callable<T> creator) {
		try {
			while(offset < stopAt) {
				GameObject obj = creator.call();
				obj.setParent(this);
				obj.getTransform().setPosition(offset, 0);
				offset += seperation;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * is safe work on?
	 * default: yes
	 */
	public boolean isSafeAt(float x) {
		return true;
	}
	
	
}
