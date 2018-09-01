package FroggerGame.Obstacles;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/*
 * How the obstacles move
 */
public class ObstactleMovement extends Component implements Updateable {
	private float speed;
	private float lengthUntilOffScreen;
	
	public ObstactleMovement(GameObject gameObject, float speed, float lengthUntilOffScreen) {
		super(gameObject);
		this.speed = speed;
		this.lengthUntilOffScreen = lengthUntilOffScreen;
	}
	
	
	@Override
	public void update() {
		//get the position
		Vector2f pos = getGameObject().getTransform().getPosition();
		//add the speed to it * delta
		pos.x += speed *getGame().getTimeDelta();
		

		float maxDistance = getScene().getCamera().getOrthographicSize()/2.0f
				+ lengthUntilOffScreen;
		
		//check if obstacle should go to other side of the screens
		if(speed > 0 && pos.x > maxDistance) 
				pos.x = -maxDistance;
		else if(speed < 0 && pos.x < -maxDistance) 
				pos.x = maxDistance;
		
		//update the position
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
