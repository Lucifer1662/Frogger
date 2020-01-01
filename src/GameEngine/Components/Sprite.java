package GameEngine.Components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.Component;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Renderable;

/**
 * Used to render an image onto the screen will always be 1x1 with the center
 * being the anchor
 * 
 * Note: used gameObjects transform to change size and position
 * 
 * @author Luke Hawkins
 */
public class Sprite extends Component implements Renderable {
	private float width = 1.0f, height = 1.0f, x = -0.5f, y = -0.5f;
	private Image image;
	private boolean isPixelPerfect, isVisible = true;

	/**
	 * Constructs a sprite with its parent the GameObject
	 * 
	 * @param gameObject The parent
	 */
	public Sprite(GameObject gameObject) {
		super(gameObject);
	}

	/**
	 * Constructs a sprite with its parent, loads the image from the image path
	 * and if it is pixel perfect
	 * 
	 * @param gameObject     The parent
	 * @param imagePath      The image path from where the image will be loaded
	 * @param isPixelPerfect If it will be renderd pixel perfect or not
	 */
	public Sprite(GameObject gameObject, String imagePath,
			boolean isPixelPerfect) {
		this(gameObject, imagePath);
		this.isPixelPerfect = isPixelPerfect;
	}

	/**
	 * Constructs a sprite with a parent, loads the image from the image path
	 * 
	 * @param gameObject The parent
	 * @param imagePath  The image path from where the image will be loaded
	 */
	public Sprite(GameObject gameObject, String imagePath) {
		this(gameObject);
		setImage(imagePath);
	}

	/**
	 * Constructs a sprite with a parent, loads the image from the image path,
	 * with the offset (x,y), the size (width, height) and if it is pixelperfect
	 * 
	 * @param gameObject     The parent
	 * @param imagePath      The image path from where the image will be loaded
	 * @param x              The offset in the x direction
	 * @param y              The offset in the y direction
	 * @param width          The width of the sprite
	 * @param height         The height of the sprite
	 * @param isPixelPerfect If the sprite is pixel perfect
	 */
	public Sprite(GameObject gameObject, String imagePath, float x, float y,
			float width, float height, boolean isPixelPerfect) {
		this(gameObject, imagePath, x, y, width, height);
		this.isPixelPerfect = isPixelPerfect;
	}

	/**
	 * Constructs a sprite with a parent, loads the image from the image path,
	 * with the offset (x,y), the size (width, height) and if it is pixelperfect
	 * 
	 * @param gameObject The parent
	 * @param imagePath  The image path from where the image will be loaded
	 * @param x          The offset in the x direction
	 * @param y          The offset in the y direction
	 * @param width      The width of the sprite
	 * @param height     The height of the sprite
	 */
	public Sprite(GameObject gameObject, String imagePath, float x, float y,
			float width, float height) {
		this(gameObject);
		setImage(imagePath);
		this.width = width;
		this.setHeight(height);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Renders the sprite
	 */
	@Override
	public void render() {
		if (!isVisible)
			return;

		// check if image is not null
		if (image != null) {
			// create positions
			Vector2f pos1 = new Vector2f(getX(), getY() + getHeight());
			Vector2f pos2 = new Vector2f(getX(), getY());
			Vector2f pos3 = new Vector2f(getX() + width, getY());
			Vector2f pos4 = new Vector2f(getX() + width, getY() + getHeight());

			// Vector2f pos1 = new Vector2f(-width, height);
			// Vector2f pos2 = new Vector2f(-width,-height);
			// Vector2f pos3 = new Vector2f(width, -height);
			// Vector2f pos4 = new Vector2f(width, height);

			// transform positions
			Transform trans = getGameObject().getTransform();
			trans.transform(pos1);
			trans.transform(pos2);
			trans.transform(pos3);
			trans.transform(pos4);

			// render
			if (isPixelPerfect)
				image.drawWarped((int) pos1.x, (int) pos1.y, (int) pos2.x,
						(int) pos2.y, (int) pos3.x, (int) pos3.y, (int) pos4.x,
						(int) pos4.y);
			else
				image.drawWarped(pos1.x, pos1.y, pos2.x, pos2.y, pos3.x, pos3.y,
						pos4.x, pos4.y);
		}
	}

	/**
	 * Gets the image
	 * 
	 * @return A copy of the image
	 */
	public Image getImage() {
		return image.copy();
	}

	/**
	 * Sets the image
	 * 
	 * @param image The image to be rendered
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Sets the image from the image that is loaded from the path
	 * 
	 * @param path The image file path
	 */
	public void setImage(String path) {
		try {
			this.image = new Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If the sprite is pixel perfect
	 * 
	 * @return Whether the sprite is pixel perfect
	 */
	public boolean isPixelPerfect() {
		return isPixelPerfect;
	}

	/**
	 * Sets pixel perfect value
	 * 
	 * @param isPixelPerfect Wheather the sprite is to be pixel perfect
	 */
	public void setPixelPerfect(boolean isPixelPerfect) {
		this.isPixelPerfect = isPixelPerfect;
	}

	/**
	 * Gets the visibility of the sprite
	 * 
	 * @return The visibility
	 */
	public boolean visibilty() {
		return isVisible;
	}

	/**
	 * Sets the visibility of the sprite
	 * 
	 * @param isVisible The visibility to be set
	 */
	public void setVisibility(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Sets the width of the sprite
	 * 
	 * @param width The width of the sprite
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * Gets the width of the sprite
	 * 
	 * @return The width of the sprite
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Gets the height of the sprite
	 * 
	 * @return The height of the sprite
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Sets the height of the sprite
	 * 
	 * @param height The height of the sprite
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * Gets the x offset of the sprite
	 * 
	 * @return The x offset of the sprite
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the x offset of the sprite
	 * 
	 * @param x The new x offset of the sprite
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets the y offset of the sprite
	 * 
	 * @return The y offset of the sprite
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the y offset of the sprite
	 * 
	 * @param y The new y offset of the sprite
	 */
	public void setY(float y) {
		this.y = y;
	}

}
