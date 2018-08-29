package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Components.Component;
import GameEngine.CoreInterfaces.Updateable;
import GameEngine.GameObjects.GameObject;

public class FrogComponent extends Component implements Updateable{
	FrogInput input;
	
	public FrogComponent(GameObject gameObject, FrogInput input) {
		super(gameObject);
		this.input = input;
	}
	@Override
	public void update() {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos = input.GetMovenent().add(pos);
		float bounding = getScene().getCamera().getOrthographicSize()/2;
		if(pos.x > -bounding && pos.x < bounding && pos.y >= 0)
			getGameObject().getTransform().setPosition(pos);
	}
	
}
