package FroggerGame.Row;
import java.util.concurrent.Callable;

import FroggerGame.Obstacles.Bus;
import GameEngine.Scene;
import GameEngine.Core.GameObject;


public class HighWayRow extends FroggerRow {
	static private final String imagePath = "assets/road.png";
	public HighWayRow(Scene scene, float rowLength, int direction) {
		super(scene, imagePath,rowLength);
		
		GameObject g = this;
		
		CreateObstacles(-rowLength/2, 4, 6, rowLength/2,  
			new Callable<Bus>() {
			public Bus call() throws Exception { 
				Bus bus = new Bus(scene, direction);
				bus.setParent(g);
				return bus;
			}
		});
	}

}
