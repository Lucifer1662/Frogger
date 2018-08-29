package FroggerGame.Frog;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Window;
import GameEngine.Core.GameObject;


public class FrogPlayerInput extends FrogInput {
	
	public FrogPlayerInput(GameObject gameObject) {
		super(gameObject);
	}

	@Override 
	public Vector2f GetMovenent() {
		Vector2f dir = new Vector2f();
		Input input = Window.getWindow().getInput();
		
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
