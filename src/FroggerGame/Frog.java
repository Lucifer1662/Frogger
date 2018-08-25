package FroggerGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Sprite;

public class Frog extends Sprite {
	FrogInput input;
	static final String frogImageLocation = "assets/frog.png";
	
	@Override 
	public void setImage(Image image){}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		super.setImage(new Image(frogImageLocation));
		
		input = (FrogInput) getGameObject().GetComponent(2);
		System.out.println("Fuck you");
	}
	
	@Override
	public void update(GameContainer container) throws SlickException {
		Vector2f pos = getGameObject().getTransform().getPosition();
		pos = input.GetMovenent().add(pos);
		getGameObject().getTransform().setPosition(pos);
		
	}
}
