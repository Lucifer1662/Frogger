package FroggerGame;
import java.util.Random;
import java.util.concurrent.Callable;

import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class FroggerRow extends GameObject{
	private static Random random = new Random();
	private static float tileOffset = 0.5f;
	FroggerRow(String tilePath, float rowLength){
		for(int i = 0; i < rowLength; i++) {
			GameObject tile = new GameObject();
			tile.setParent(this);
			tile.getTransform().setPosition(i-rowLength/2.0f + tileOffset,0);
			tile.AddComponent(new Sprite(tilePath, true));
		}
	}
	
	FroggerRow(String tilePath, float rowLength, GameObject[] objects, float[] offsets){
		this(tilePath, rowLength);
		rowLength /=2;
		assert(objects.length == offsets.length);
		for(int i =0; i < objects.length; i++) {
			rowLength += offsets[i];
			objects[i].setParent(this);
			objects[i].getTransform().setPosition(rowLength, 0);
		}
	}
	
	<T extends GameObject>
	FroggerRow(String tilePath, float rowLength, Callable<T> creator, float[] offsets){
		this(tilePath, rowLength);
		rowLength /=2;
		try {
			for(int i =0; i < offsets.length; i++) {
				GameObject obj = creator.call();
				rowLength += offsets[i];
				obj.setParent(this);
				obj.getTransform().setPosition(rowLength, 0);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	<T extends GameObject>
	FroggerRow(String tilePath, float rowLength, Callable<T> creator, float minOffset, float maxOffset, float stopAt){
		this(tilePath, rowLength);
		CreateObstacles(-rowLength/2, minOffset, maxOffset, stopAt, creator);
	}
	
	
	
	<T extends GameObject>
	void CreateObstacles(float start, float minOffset, float maxOffset, float stopAt, Callable<T> creator) {
		try {
			start += random.nextFloat()*(maxOffset-minOffset)+minOffset;
			while(start < stopAt) {
				GameObject obj = creator.call();
				obj.setParent(this);
				obj.getTransform().setPosition(start, 0);
				
				start += random.nextFloat()*(maxOffset-minOffset)+minOffset;
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
