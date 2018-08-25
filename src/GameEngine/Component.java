package GameEngine;
import org.newdawn.slick.SlickException;

public abstract class Component {
	//gameObject is not private because i want it to be shared with GameObject
	//so it can be initiated without putting it through the constructor
	//because when inherited from the subclass has that control
	//which i don't want
	
	private GameObject gameObject;	
	
	public void render() throws SlickException {}
	public void update() throws SlickException {}
		
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void SetGameObjectAttachedTo(GameObject gameObject) {
		if(this.gameObject != null)
			this.gameObject.RemoveComponent(this);
		
		this.gameObject = gameObject;
	}
		
}
