package FroggerGame.Frog;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Window;
import GameEngine.Core.GameObject;

/*
 * A frog component that can be controlled by the player
 */
public class FrogComponentPlayer extends FrogComponent{
	
	public FrogComponentPlayer(GameObject gameObject) {
		super(gameObject);
	}


	@Override
	public Vector2f GetMovenent() {
		Vector2f dir = new Vector2f();
		Input input = Window.getWindow().getInput();
		
		//get direction from the keys
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
