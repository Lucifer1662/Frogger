package FroggerGame.Obstacles;


import GameEngine.Scene;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class Bus extends GameObject {
	private static final String busImageLocation = "assets/bus.png";
	//speed in pixels per second
	private static final float speed = 150;
	private static final int indexOfObstacleMovement = 2;
	private static final float width = 0.4f, height = 0.4f;
	private int direction = 1;
	
	public Bus(Scene scene, int direction) {
		super(scene);
		this.direction = direction;
		AddComponent(new Sprite(this, busImageLocation, true));
		AddComponent(new BoundingBox(this, -width, -height, 2*width, 2*height));
		ObstactleMovement obstacleMovement = AddComponent(new ObstactleMovement(this));
		obstacleMovement.setlengthUntilOffScreen(width);
	}
	
	@Override
	public void init() {
		super.init();
		((ObstactleMovement)getComponent(indexOfObstacleMovement))
		.setSpeed(getWindow().getPixelToUnit() * direction * speed);
	}
	
}
