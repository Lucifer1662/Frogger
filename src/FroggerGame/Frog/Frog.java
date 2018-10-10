package FroggerGame.Frog;

import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Components.CollisionComponents.OnCollisionKill;
import FroggerGame.Interfaces.Alive;
import FroggerGame.Interfaces.Pushable;
import FroggerGame.Interfaces.Rider;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.BoundingBox;
import GameEngine.Components.Sprite;
import GameEngine.Core.GameObject;

/**
 * Frog gameObject that can be controlled by the player
 * 
 * @author Luke Hawkins
 */
public class Frog extends GameObject implements Rider, Pushable, Alive {
	public static final String FROG_IMAGE_LOCATION = "assets/frog.png";
	private final static float WIDTH = 0.4f, HEIGHT = 0.4f;
	private static final Vector2f FROG_START_POSITION = new Vector2f(0, 2);

	private int numberOfLives = 3;

	/**
	 * Constructs frog in a scene
	 * 
	 * @param scene The scene in
	 */
	public Frog(Scene scene) {
		super(scene);
		// Add components
		new FrogComponentPlayer(this);
		new Sprite(this, FROG_IMAGE_LOCATION);
		new BoundingBox(this, -WIDTH, -HEIGHT, 2 * WIDTH, 2 * HEIGHT);
		new OnCollisionKill(this, this);
		getTransform().setPosition(FROG_START_POSITION);

	}

	/**
	 * Pushes the frog in a direction
	 * 
	 * @param dir The direction to push the frog
	 */
	@Override
	public void push(Vector2f dir) {
		Vector2f pos = getTransform().getPosition();
		pos = pos.add(dir);
		getTransform().setPosition(pos);
	}

	/**
	 * Causes the frog to mount the carrier
	 * 
	 * @param carrier What the frog will mount
	 */
	@Override
	public void mount(GameObject carrier) {
		setParent(carrier);
		System.out.println("Mount: " + carrier.getClass().getName());
	}

	/**
	 * The frog dismounts
	 * 
	 * @param What was dismounted
	 */
	@Override
	public void dismount(GameObject carrier) {
		if (getParent() == carrier) {
			setParent(getScene().getCamera());
			System.out.println("Exit LogOn");
		} else
			System.out.println("Log Swap");
	}

	/**
	 * Resets the frog to its original position
	 */
	public void Reset() {
		setParent(getScene().getCamera());
		getTransform().setPosition(FROG_START_POSITION);
	}

	/**
	 * Gets the number of lives the frog has
	 * 
	 * @return The number of lives the frog has
	 */
	public int getNumberOfLives() {
		return numberOfLives;
	}

	/**
	 * Adds a life to the frog
	 */
	@Override
	public void addLife() {
		numberOfLives++;
	}

	/**
	 * Removes a life from the frog, and resets it
	 */
	@Override
	public void removeLife() {
		numberOfLives--;
		if (numberOfLives <= 0)
			Window.getWindow().exit();
		Reset();
	}

}
