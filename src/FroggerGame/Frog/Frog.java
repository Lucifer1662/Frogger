package FroggerGame.Frog;

import GameEngine.Scene;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class Frog extends GameObject {
	private static final String frogImageLocation = "assets/frog.png";
	private final float width = 0.4f, height = 0.4f, maxy = 8;
	
	public Frog(Scene scene) {
		super(scene);
		FrogPlayerInput input = AddComponent(new FrogPlayerInput(this));
		AddComponent(new FrogComponent(this, input));	
		AddComponent(new Sprite(this, frogImageLocation));
		AddComponent(new BoundingBox(this, -width, -height, 2*width, 2*height));
		AddComponent(new ExitGameOnCollision(this));
		AddComponent(new ExitGamePastYPos(this, maxy));
	}
}
