package FroggerGame.Obstacles.Concrete;

import org.newdawn.slick.geom.Vector2f;

import FroggerGame.Components.CollisionComponents.OnCollisionAddLife;
import FroggerGame.Components.CollisionComponents.OnCollisionWithFrogDestroy;
import FroggerGame.Interfaces.Rider;
import FroggerGame.Obstacles.Obstactle;
import GameEngine.Scene;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.Updateable;

/**
 * An extra life object that the player can collide with to get an additional
 * life
 * 
 * @author Luke Hawkins
 *
 */
public class ExtraLife extends Obstactle implements Rider, Updateable {

	private static final String IMAGE_LOCATION = "assets/extralife.png";
	private static final float WIDTH = 1.0f, HEIGHT = 1.0f, MOVE_SPEED = 2;
	private float moveDistance;
	private float direction = 1;
	private float timeTillMove = MOVE_SPEED;

	/**
	 * Constructs an extra life in a scen, with a certain move distance
	 * 
	 * @param scene        The scene in
	 * @param moveDistance The distance away from 0 in the x direction
	 */
	public ExtraLife(Scene scene, float moveDistance) {
		super(scene, WIDTH, HEIGHT, IMAGE_LOCATION);
		new OnCollisionAddLife(this);
		new OnCollisionWithFrogDestroy(this);
		this.moveDistance = moveDistance;
	}

	/**
	 * Updates the extra life
	 */
	@Override
	public void update() {
		timeTillMove -= getGame().getTimeDelta();
		if (timeTillMove <= 0) {
			timeTillMove = MOVE_SPEED;
			Vector2f pos = getTransform().getPosition();
			if (Math.abs(pos.x + direction) > moveDistance)
				direction = -direction;

			pos.x += direction;
			getTransform().setPosition(pos);
		}
	}

	@Override
	public boolean isSafe() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	/**
	 * Mounts the extra life on a gameObject
	 */
	@Override
	public void mount(GameObject carrier) {
		setParentLocal(carrier);
	}

	/**
	 * Dismount the extra life on a gameObject
	 */
	@Override
	public void dismount(GameObject carrier) {
		setParent(getScene().getCamera());
	}

}
