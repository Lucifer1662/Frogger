package FroggerGame;

import GameEngine.BoundingBox;
import GameEngine.GameObject;
import GameEngine.Sprite;

public class Bus extends GameObject {
	private static final String busImageLocation = "assets/bus.png";
	private final float width = 0.5f, height = 0.5f;
	
	public Bus() {
		AddComponent(new Sprite(busImageLocation));
		AddComponent(new BoundingBox(-width, -height, 2*width, 2*height));
	}
}
