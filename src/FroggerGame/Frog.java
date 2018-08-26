package FroggerGame;

import GameEngine.BoundingBox;
import GameEngine.GameObject;
import GameEngine.Sprite;

public class Frog extends GameObject {
	private static final String frogImageLocation = "assets/frog.png";
	private final float width = 0.5f, height = 0.5f;
	
	public Frog() {
		FrogPlayerInput input = AddComponent(new FrogPlayerInput());
		AddComponent(new FrogComponent(input));	
		AddComponent(new Sprite(frogImageLocation));
		AddComponent(new BoundingBox(-width, -height, 2*width, 2*height));
	}
}
