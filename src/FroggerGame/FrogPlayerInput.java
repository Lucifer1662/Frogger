package FroggerGame;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;


public class FrogPlayerInput extends FrogInput {
	
	@Override 
	public Vector2f GetMovenent() {
		Vector2f dir = new Vector2f();
		Input input = getGameObject().getWindow().getInput();
		
		if(input.isKeyPressed(Input.KEY_UP)) 
			dir.y = 1;
		else if(input.isKeyPressed(Input.KEY_DOWN)) 
			dir.y = -1;
		else if(input.isKeyPressed(Input.KEY_LEFT)) 
			dir.x = -1;
		else if(input.isKeyPressed(Input.KEY_RIGHT)) 
			dir.x = 1;
		
		return dir;
	}
}
