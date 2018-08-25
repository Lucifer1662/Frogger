package FroggerGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Sprite;

public class FrogComponent extends Sprite {
	FrogInput input;
	private static final String frogImageLocation = "assets/frog.png";
	
	//delete the setter for image
	@Override 
	public void setImage(Image image){}
	
	public FrogComponent(FrogInput input) {
		super.setImage(frogImageLocation);
		this.input = input;
	}
	
	@Override
	public void update() throws SlickException {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos = input.GetMovenent().add(pos);
		getGameObject().getTransform().setPosition(pos);
	}
	
	
}
