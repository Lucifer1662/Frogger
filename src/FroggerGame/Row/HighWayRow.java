package FroggerGame.Row;
import java.util.concurrent.Callable;

import FroggerGame.Obstacles.Bus;
import GameEngine.Scene;
import GameEngine.Core.GameObject;


public class HighWayRow extends FroggerRow {
	static private final String IMAGE_FILE_PATH = "assets/road.png";
	
	public HighWayRow(Scene scene, float rowLength, int direction, float offset, float seperation) {
		super(scene, IMAGE_FILE_PATH,rowLength);
		
		GameObject gameObject = this;
		
		//Create obstacles
		CreateObstacles(-rowLength/2 + offset, seperation, rowLength/2,  
			new Callable<Bus>() {
			public Bus call() throws Exception { 
				Bus bus = new Bus(scene, direction);
				bus.setParent(gameObject);
				return bus;
			}
		});
		
	}

}
