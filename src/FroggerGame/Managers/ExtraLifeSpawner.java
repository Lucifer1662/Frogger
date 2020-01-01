package FroggerGame.Managers;

import java.util.List;
import java.util.Random;

import FroggerGame.Obstacles.RideableObstacle;
import FroggerGame.Obstacles.Concrete.ExtraLife;
import FroggerGame.Obstacles.Concrete.Log;
import GameEngine.Scene;
import GameEngine.Core.GameObject;
import GameEngine.CoreInterfaces.OnDestroy;
import GameEngine.CoreInterfaces.Updateable;

/**
 * A class that spawns extra lifes on logs
 * 
 * @author lhawk
 *
 */
public class ExtraLifeSpawner extends GameObject
		implements Updateable, OnDestroy {

	private List<Log> logs;
	private final static float MIN_TIME = 25, MAX_TIME = 35;
	private float tillNextSpawn;
	private static Random random = new Random();
	private boolean frogSpawned = false;

	/**
	 * Constructs an extra life spawner in a scene, with a set of logs
	 * associated wtih
	 * 
	 * @param scene The scene in
	 * @param logs  The logs the extra life will spawn on
	 */
	public ExtraLifeSpawner(Scene scene, List<Log> logs) {
		super(scene);
		this.logs = logs;
		setNewTimeTillNextSpawn();
	}

	/**
	 * Updates the extra life spawn
	 */
	@Override
	public void update() {
		tillNextSpawn -= getGame().getTimeDelta();
		if (tillNextSpawn <= 0) {
			setNewTimeTillNextSpawn();
			spawnExtraLife();
		}
	}

	private void setNewTimeTillNextSpawn() {
		tillNextSpawn = random.nextFloat() * (MAX_TIME - MIN_TIME) + MIN_TIME;
	}

	private void spawnExtraLife() {
		if (!frogSpawned) {
			Log log = logs.get(random.nextInt(logs.size() - 1));
			ExtraLife life = new ExtraLife(getScene(),
					log.getSprite().getWidth() / 2.0f);
			life.mount(log);
			frogSpawned = true;
			life.addNotifyOnDestroy(this);
		}

	}

	/**
	 * The callback for when a frog life is destroyed
	 */
	@Override
	public void onDestroy(GameObject gameObject) {
		if (gameObject instanceof ExtraLife)
			frogSpawned = false;
	}

}
