package FroggerGame;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Component;
import GameEngine.Collision.Collider;

public class FrogComponent extends Component {
	FrogInput input;
	
	public FrogComponent(FrogInput input) {
		this.input = input;
	}
	
	@Override
	public void update() throws SlickException {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos = input.GetMovenent().add(pos);
		getGameObject().getTransform().setPosition(pos);
	}
	
	@Override
	public void onCollision(Collider col) {
		System.out.println("Frog is Colliding");
	}
	
}
