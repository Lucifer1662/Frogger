package FroggerGame.Row;

import GameEngine.Scene;

public class GrassRow extends FroggerRow {
	private static String grassImageLocation = "assets/grass.png";
	public GrassRow(Scene scene, float rowLength){
		super(scene, grassImageLocation, rowLength);
	}
	
}
