package FroggerGame.Frog;

import java.util.List;

import FroggerGame.Components.ExitGameOnCollision;
import FroggerGame.Components.ExitGameWhenInUnsafePosition;
import FroggerGame.Row.FroggerRow;
import GameEngine.Scene;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/*
 * Frog gameObject that can be controlled by the player
 */
public class Frog extends GameObject {
	private static final String FROG_IMAGE_LOCATION = "assets/frog.png";
	private final static float WIDTH = 0.4f, HEIGHT = 0.4f;
	
	public Frog(Scene scene, List<FroggerRow> rows) {
		super(scene);
		//Add components
		new FrogComponentPlayer(this);	
		new Sprite(this, FROG_IMAGE_LOCATION);
		new BoundingBox(this, -WIDTH, -HEIGHT, 2*WIDTH, 2*HEIGHT);
		new ExitGameOnCollision(this);
		new ExitGameWhenInUnsafePosition(this, rows);
	}
}
