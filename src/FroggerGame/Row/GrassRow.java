package FroggerGame.Row;

import GameEngine.Scene;

public class GrassRow extends FroggerRow {
	private static String IMAGE_FILE_PATH = "assets/grass.png";
	public GrassRow(Scene scene, float rowLength){
		super(scene, IMAGE_FILE_PATH, rowLength);
	}
}
