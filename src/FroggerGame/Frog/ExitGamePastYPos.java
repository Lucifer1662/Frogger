package FroggerGame.Frog;

import GameEngine.Window;
import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

public class ExitGamePastYPos extends Component implements Updateable{
	private float maxy;
	public ExitGamePastYPos(GameObject gameObject, float maxy) {
		super(gameObject);
		this.maxy = maxy;
	}
	@Override
	public void update() {
		if(getGameObject().getTransform().getPosition().y >= maxy)
			Window.getWindow().exit();
	}
	
	
	
}
