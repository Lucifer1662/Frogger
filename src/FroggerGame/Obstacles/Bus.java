package FroggerGame.Obstacles;


import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

public class Bus extends GameObject {
	private static final String busImageLocation = "assets/bus.png";
	//speed in pixels per second
	private static final float speed = 150;
	private static final float width = 0.4f, height = 0.4f;
	
	public Bus(Scene scene, int direction) {
		super(scene);
		
		new Sprite(this, busImageLocation, true);
		new BoundingBox(this, -width, -height, 2*width, 2*height);
		new ObstactleMovement(this,
				Window.getWindow().getPixelToUnit(scene) * direction * speed, width);
	}

	
}
