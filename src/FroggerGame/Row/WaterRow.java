package FroggerGame.Row;

import GameEngine.Scene;

public class WaterRow extends FroggerRow{
	private static String waterImageLocation = "assets/water.png";
	public WaterRow(Scene scene, float rowLength){
		super(scene, waterImageLocation, rowLength);
	}
}
