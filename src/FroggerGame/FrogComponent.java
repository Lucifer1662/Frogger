package FroggerGame;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Components.Collider;
import GameEngine.Components.Component;
import GameEngine.CoreInterfaces.OnCollideable;
import GameEngine.CoreInterfaces.Updateable;

public class FrogComponent extends Component implements Updateable, OnCollideable{
	FrogInput input;
	
	public FrogComponent(FrogInput input) {
		this.input = input;
	}
	@Override
	public void update() {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos = input.GetMovenent().add(pos);
		float bounding = getScene().getCamera().getOrthographicSize()/2;
		if(pos.x > -bounding && pos.x < bounding && pos.y > 0)
			
		getGameObject().getTransform().setPosition(pos);
	}
	
	@Override
	public void onCollision(Collider col) {
		System.out.println("Frog is Colliding");
	}
	
}
