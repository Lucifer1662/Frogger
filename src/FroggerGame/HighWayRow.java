package FroggerGame;
import java.util.concurrent.Callable;


public class HighWayRow extends FroggerRow {
	static private final String imagePath = "assets/road.png";
	HighWayRow(float rowLength, int direction) {
		super(imagePath,rowLength);
		
		CreateObstacles(-rowLength/2, 2, 4, rowLength/2,  
			new Callable<Bus>() {
			public Bus call() throws Exception { return new Bus(direction);
			}
		});
	}

}
