package FroggerGame;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Components.Component;
import GameEngine.CoreInterfaces.Updateable;

public class ObstactleMovement extends Component implements Updateable {
	private float speed;
	private float lengthUntilOffScreen;
	
	public ObstactleMovement(float speed, float lengthUntilOffScreen) {
		this.speed = speed;
		this.lengthUntilOffScreen = lengthUntilOffScreen;
	}
	
	@Override
	public void update() {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos.x += speed * getGameObject().getScene().getGame().getTimeDelta();
		getGameObject().getTransform().setPosition(pos);
		if(speed > 0) {
			
		}
	}
	
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getlengthUntilOffScreen() {
		return lengthUntilOffScreen;
	}
	public void setlengthUntilOffScreen(float lengthUntilOffScreen) {
		this.lengthUntilOffScreen = lengthUntilOffScreen;
	}
	
}
