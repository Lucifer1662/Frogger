package GameEngine.Components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.CoreInterfaces.Renderable;
import GameEngine.GameObjects.GameObject;

public class Sprite extends Component implements Renderable {
	private float width = 0.5f, height = 0.5f;
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
	float angle = 0;
	@Override
	public void render() {
		if(image != null) {
			Vector2f pos1 = new Vector2f(-width, height);	
			Vector2f pos2 = new Vector2f(-width,-height);
			Vector2f pos3 = new Vector2f(width, -height);
			Vector2f pos4 = new Vector2f(width, height);
	
			
			Transform trans = getGameObject().getTransform();
			trans.transform(pos1);
			trans.transform(pos2);
			trans.transform(pos3);
			trans.transform(pos4);
				
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
