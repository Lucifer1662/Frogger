package FroggerGame;


import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class Bus extends GameObject {
	private static final String busImageLocation = "assets/bus.png";
	//speed in pixels per second
	private static final float speed = 150;
	private static final int indexOfObstacleMovement = 2;
	private static final float width = 0.5f, height = 0.5f;
	private int direction = 1;
	public Bus(int direction) {
		this.direction = direction;
		AddComponent(new Sprite(busImageLocation, true));
		AddComponent(new BoundingBox(-width, -height, 2*width, 2*height));
		ObstactleMovement obstacleMovement = AddComponent(new ObstactleMovement());
		obstacleMovement.setlengthUntilOffScreen(width);
		new GameObject().setParent(this);
	}
	
	@Override
	public void init() {
		super.init();
		((ObstactleMovement)getComponent(indexOfObstacleMovement))
		.setSpeed(getWindow().getPixelToUnit() * direction * speed);
	}
	
}
