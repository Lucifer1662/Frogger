package FroggerGame;

import GameEngine.Game;
import GameEngine.Scene;

public class MainLevel extends Scene{

	private static float screenWidth = 21;
	private static int numOfHighWayLanes = 5;
	private static int numOfWaterRows = 6;
	public MainLevel(Game game) {
		super(game);
		
		getCamera().SetOrthographicSize(getWindow(), screenWidth);
		getCamera().SetPoistion(0, screenWidth/2.0f + 2);
		int i=1;
		FroggerRow row = new FroggerRow("assets/grass.png",screenWidth);
		row.setScene(this);
		row.getTransform().setPosition(0, i++);
		
		for(int length = i + numOfHighWayLanes; i < length; i ++) {
			row = new HighWayRow(21, (i%2) == 0 ? -1: 1);
			row.setScene(this);
			row.getTransform().setPosition(0,i);
		}

		row = new FroggerRow("assets/grass.png", screenWidth);
		row.setScene(this);
		row.getTransform().setPosition(0, i++);
		
		for(int length = i + numOfWaterRows; i < length; i ++) {
			row = new WaterRow(21);
			row.setScene(this);
			row.getTransform().setPosition(0,i);
		}
		
		
		Frog frog = new Frog();
		frog.setScene(this);
		frog.getTransform().setPosition(0,0);
		
		
		
		
	}
	
}
