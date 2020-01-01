package GameEngine;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Core.GameObject;
import GameEngine.Core.UserInterfaceObject;

/**
 * A gameobject to act like a camera for its children when transformed
 * 
 * @author Luke Hawkins
 */

public class Camera extends GameObject {
	private float orthographicSize = 10;
	private UserInterfaceObject userInterface;

	/**
	 * Constructs a camera within a scene and attached to a parent
	 * 
	 * @param scene  The scene the camera is in
	 * @param parent The parent gameObject attached to(null is the world)
	 */
	public Camera(Scene scene, GameObject parent) {
		super(scene, parent);

		int width = Window.getWindow().getWidth();
		int height = Window.getWindow().getHeight();
		float widthOrto = width / orthographicSize;

		// Transform world root so the origin is in the middle and
		// screen is orthographicSize in units across in the width
		getTransform().Apply(width / 2.0f, height / 2.0f, widthOrto, -widthOrto,
				0.0f);
		userInterface = new UserInterfaceObject(scene, null);
		userInterface.getTransform().Apply(width / 2.0f, height / 2.0f,
				widthOrto, -widthOrto, 0.0f);
	}

	/**
	 * Renders the camera and its children
	 */
	@Override
	public void render() {
		super.render();
		userInterface.render();
	}

	/**
	 * Updates the camera and its children
	 */
	@Override
	public void update() {
		super.update();
		userInterface.update();
	}

	/**
	 * Sets the position of the camera
	 * 
	 * @param x The x position in world space
	 * @param y The y position in world space
	 */
	public void SetPoistion(float x, float y) {
		int width = Window.getWindow().getWidth();
		int height = Window.getWindow().getHeight();
		getTransform().setPosition(
				width / 2.0f + x * Window.getUnitToPixels(getScene()),
				height / 2.0f + y * Window.getUnitToPixels(getScene()));
	}

	/**
	 * Sets the position of the camera
	 * 
	 * @param pos The position in world space
	 */
	public void SetPoistion(Vector2f pos) {
		SetPoistion(pos.x, pos.y);
	}

	/**
	 * Sets the orthographic size
	 * 
	 * @param size The orthographic size
	 */
	public void SetOrthographicSize(float size) {
		orthographicSize = size;
		float scale = Window.getWindow().getWidth() / size;
		getTransform().setScale(scale, -scale);
		userInterface.getTransform().setScale(scale, -scale);
	}

	/**
	 * Gets the orthographic size
	 * 
	 * @return The orthographic size
	 */
	public float getOrthographicSize() {
		return orthographicSize;
	}

	/**
	 * Gets the vertical orthographic size
	 * 
	 * @return The vertical orthographic size
	 */
	public float getVerticalOrthoSize() {
		float aspect = (float) Window.getWindow().getHeight()
				/ Window.getWindow().getWidth();
		return orthographicSize * ((float) Window.getWindow().getHeight()
				/ Window.getWindow().getWidth());

	}

	/**
	 * Converts a world position to screen coordinates
	 * 
	 * @param pos The position in screen space
	 */
	public void convertToWorldFromScreen(Vector2f pos) {
		int width = Window.getWindow().getWidth();
		int height = Window.getWindow().getHeight();
		pos.x = (pos.x) * Window.getPixelToUnit(getScene());
		pos.y = (pos.y - height / 2.0f) * Window.getPixelToUnit(getScene());

	}

	/**
	 * Gets the user interface root
	 * 
	 * @return The user interface root
	 */
	public UserInterfaceObject getUserInterface() {
		return userInterface;
	}

}
