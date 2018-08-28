package FroggerGame;
import GameEngine.Components.Sprite;
import GameEngine.GameObjects.GameObject;

public class FroggerRow extends GameObject{
	private static final int rowLength = 21;
	FroggerRow(String tilePath){
		for(int i = 0; i < rowLength; i++) {
			GameObject tile = new GameObject();
			tile.setParent(this);
			tile.getTransform().setPosition(i-rowLength/2,0);
			tile.AddComponent(new Sprite(tilePath, true));
		}
	}
}
