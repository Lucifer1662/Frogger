package FroggerGame.Frog;

import GameEngine.Components.Component;
import GameEngine.CoreInterfaces.Updateable;
import GameEngine.GameObjects.GameObject;

public class ExitGamePastYPos extends Component implements Updateable{
	private float maxy;
	public ExitGamePastYPos(GameObject gameObject, float maxy) {
		super(gameObject);
		this.maxy = maxy;
	}
	@Override
	public void update() {
		if(getGameObject().getTransform().getPosition().y >= maxy)
			getWindow().exit();
	}
	
	
	
}
