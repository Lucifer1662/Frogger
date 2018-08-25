package GameEngine;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Sprite extends Component {
	private float width =0.5f, height = 0.5f;
	private Image image;
	
	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		Vector2f pos1 = new Vector2f(width,height);
		Vector2f pos2 = new Vector2f(-width,-height);
		
		getGameObject().getTransform().Mult(pos1);
		getGameObject().getTransform().Mult(pos2);
		
		
		image.draw(pos1.x, pos1.y,
				pos2.x - pos1.x, pos2.y - pos1.y);
	}

	public Image getImage() {
		return image.copy();
	}

	public void setImage(Image image) {
		this.image = image;
	}

}