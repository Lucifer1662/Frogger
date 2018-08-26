package GameEngine;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Matrix2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Sprite extends Component {
	private float width = 0.5f, height = 0.5f;
	private Image image;
	
	public Sprite() {}
	public Sprite(String imagePath) {
		setImage(imagePath);
	}
	float angle = 0;
	@Override
	public void render() throws SlickException {
		if(image != null) {
			Vector2f pos1 = new Vector2f(-width,-height);
			Vector2f pos2 = new Vector2f(width, -height);
			Vector2f pos3 = new Vector2f(width, height);
			Vector2f pos4 = new Vector2f(-width, height);	
			
			Transform trans = getGameObject().getTransform();
			trans.transform(pos1);
			trans.transform(pos2);
			trans.transform(pos3);
			trans.transform(pos4);
				
						
			image.drawWarped(
					pos1.x, pos1.y,
					pos2.x, pos2.y,
					pos3.x, pos3.y,
					pos4.x, pos4.y
					);
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

}
