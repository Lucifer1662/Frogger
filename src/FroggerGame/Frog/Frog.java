package FroggerGame.Frog;

import GameEngine.Scene;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

public class Frog extends GameObject {
	private static final String frogImageLocation = "assets/frog.png";
	private final float width = 0.4f, height = 0.4f, maxy = 8;
	
	public Frog(Scene scene) {
		super(scene);
		FrogPlayerInput input = new FrogPlayerInput(this);
		new FrogComponent(this, input);	
		new Sprite(this, frogImageLocation);
		new BoundingBox(this, -width, -height, 2*width, 2*height);
		new ExitGameOnCollision(this);
		new ExitGamePastYPos(this, maxy);
	}
}
