package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/*
 * Represents a frog behavior component
 * that doesn't know how to move
 */
public abstract class FrogComponent extends Component implements Updateable, ControlableMovement{
	private static final float MIN_Y = -1;
	public FrogComponent(GameObject gameObject) {
		super(gameObject);
	}
	
	@Override
	public void update() {
		//get the position
		Vector2f pos = getGameObject().getTransform().getPosition();
		//add the movement
		pos = GetMovenent().add(pos);
		//check we still in bounds and then apply position
		float bounding = getScene().getCamera().getOrthographicSize()/2;
		if(pos.x > -bounding && pos.x < bounding && pos.y >= MIN_Y)
			getGameObject().getTransform().setPosition(pos);
	}

	
	
}
