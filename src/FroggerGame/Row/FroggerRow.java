package FroggerGame.Row;
import java.util.Random;
import java.util.concurrent.Callable;

import GameEngine.Scene;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

public class FroggerRow extends GameObject{
	private static Random random = new Random();
	private static float tileOffset = 0.5f;
	public FroggerRow(Scene scene, String tilePath, float rowLength){
		super(scene);
		for(int i = 0; i < rowLength; i++) {
			GameObject tile = new GameObject(scene,this);
			tile.getTransform().setPosition(i-rowLength/2.0f + tileOffset,0);
			new Sprite(tile, tilePath, true);
		}
	}
	
	public FroggerRow(Scene scene ,String tilePath, float rowLength, GameObject[] objects, float[] offsets){
		this(scene, tilePath, rowLength);
		rowLength /=2;
		assert(objects.length == offsets.length);
		for(int i =0; i < objects.length; i++) {
			rowLength += offsets[i];
			objects[i].setParent(this);
			objects[i].getTransform().setPosition(rowLength, 0);
		}
	}
	
	public <T extends GameObject>
	FroggerRow(Scene scene, String tilePath, float rowLength, Callable<T> creator, float[] offsets){
		this(scene, tilePath, rowLength);
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
	
	
	public <T extends GameObject>
	FroggerRow(Scene scene, String tilePath, float rowLength, Callable<T> creator, float minOffset, float maxOffset, float stopAt){
		this(scene, tilePath, rowLength);
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
