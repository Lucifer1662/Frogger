package FroggerGame.Row;

import GameEngine.Scene;

public class WaterRow extends FroggerRow{
	private static final String WATER_IMAGE_PATH = "assets/water.png";
	public WaterRow(Scene scene, float rowLength){
		super(scene, WATER_IMAGE_PATH, rowLength);
	}
	
	@Override
	public boolean isSafeAt(float x) {
		return false;
	}
}
