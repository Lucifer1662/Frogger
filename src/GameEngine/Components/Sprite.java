package GameEngine.Components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Renderable;

/*
 * Used to render an image onto the screen
 * will always be 1x1 with the center being the anchor
 * 
 * Note: used gameObjects transform to change size and position
 */
public class Sprite extends Component implements Renderable {
	private static float WIDTH = 0.5f, HEIGHT = 0.5f;
	private Image image;
	private boolean isPixelPerfect;
	
	public Sprite(GameObject gameObject) {
		super(gameObject);
	}
	
	public Sprite(GameObject gameObject, String imagePath, boolean isPixelPerfect) {
		this(gameObject, imagePath);
		this.isPixelPerfect = isPixelPerfect;
	}
	
	public Sprite(GameObject gameObject, String imagePath) {
		this(gameObject);
		setImage(imagePath);
	}
	
	@Override
	public void render() {
		//check if image is not null
		if(image != null) {
			//create positions
			Vector2f pos1 = new Vector2f(-WIDTH, HEIGHT);	
			Vector2f pos2 = new Vector2f(-WIDTH,-HEIGHT);
			Vector2f pos3 = new Vector2f(WIDTH, -HEIGHT);
			Vector2f pos4 = new Vector2f(WIDTH, HEIGHT);
	
			//transform positions
			Transform trans = getGameObject().getTransform();
			trans.transform(pos1);
			trans.transform(pos2);
			trans.transform(pos3);
			trans.transform(pos4);
				
			//render 
			if(isPixelPerfect)			
				image.drawWarped(
					(int)pos1.x, (int)pos1.y,
					(int)pos2.x, (int)pos2.y,
					(int)pos3.x, (int)pos3.y,
					(int)pos4.x, (int)pos4.y);
			else
				image.drawWarped(
					pos1.x, pos1.y,
					pos2.x, pos2.y,
					pos3.x, pos3.y,
					pos4.x, pos4.y);
		}
	}

	public Image getImage() {
		return image.copy();
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setImage(String path) {
		try {
			this.image = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public boolean isPixelPerfect() {
		return isPixelPerfect;
	}
	public void setPixelPerfect(boolean isPixelPerfect) {
		this.isPixelPerfect = isPixelPerfect;
	}
	
	

}
