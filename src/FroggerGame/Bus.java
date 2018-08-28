package FroggerGame;

import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class Bus extends GameObject {
	private static final String busImageLocation = "assets/bus.png";
	private final float width = 0.5f, height = 0.5f;
	
	public Bus() {
		AddComponent(new Sprite(busImageLocation));
		AddComponent(new BoundingBox(-width, -height, 2*width, 2*height));
	}
}
