package FroggerGame.Components;

import java.util.List;

import FroggerGame.Row.FroggerRow;
import GameEngine.Window;
import GameEngine.Components.Transform;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/*
 * Exits the game when the gameObject in an unsafe position
 */
public class ExitGameWhenInUnsafePosition extends Component implements Updateable{
	private List<FroggerRow> rows;
	
	public ExitGameWhenInUnsafePosition(GameObject gameObject, List<FroggerRow> rows) {
		super(gameObject);
		this.rows = rows;
	}

	@Override
	public void update() {
		Transform trans = getGameObject().getTransform();
		int index = (int)trans.getPosition().y;
		
		//check not out of bounds
		if(index >= 0 && index < rows.size()) {
			FroggerRow row = rows.get(index);
			
			//checks that the row is valid and if unsafe exit game
			if(row != null && !row.isSafeAt(trans.getPosition().x))
				Window.getWindow().exit();
			
		}
	}
	
	
}
