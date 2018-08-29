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
	
	public ObstactleMovement() {}
	
	@Override
	public void update() {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos.x += speed *getGame().getTimeDelta();
		float maxDistance = getScene().getCamera().getOrthographicSize()/2.0f
				+ lengthUntilOffScreen;
		if(speed > 0) {
			if(pos.x > maxDistance) {
				pos.x = -maxDistance;
			}
		}else {
			if(pos.x < -maxDistance) {
				pos.x = maxDistance;
			}
		}
		getGameObject().getTransform().setPosition(pos);
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
		if(lengthUntilOffScreen > 0)
			this.lengthUntilOffScreen = lengthUntilOffScreen;
	}
	
}
