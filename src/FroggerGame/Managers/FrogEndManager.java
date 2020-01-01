package FroggerGame.Managers;

import FroggerGame.Obstacles.Concrete.FrogEnd;
import GameAndScene.FroggerScene;
import GameEngine.Scene;
import GameEngine.Core.GameObject;

/**
 * A class that manages all the frog ends in a scene
 * 
 * @author Luke Hawkins
 *
 */
public class FrogEndManager extends GameObject {
	private static int NUMBER_OF_FROGENDS = 5;
	private static float OFFSET = 3.5f, SPACING = 4.0f;
	private int numOfFrogEndsCompleted = 0;

	/**
	 * Constructs a FrogEndManager in a scene
	 * 
	 * @param scene The parent scene
	 */
	public FrogEndManager(Scene scene) {
		super(scene);
		for (int i = 0; i < NUMBER_OF_FROGENDS; i++) {
			FrogEnd frogEnd = new FrogEnd(getScene(), this);
			frogEnd.setParent(this);
			frogEnd.getTransform().setPosition(
					-getScene().getCamera().getOrthographicSize() / 2.0f
							+ OFFSET + SPACING * i,
					0);
		}
	}

	/**
	 * adds a frog end to the completed list, one all are complete the next
	 * level is loaded
	 */
	public void addFrogEndCompleted() {
		numOfFrogEndsCompleted++;
		if (numOfFrogEndsCompleted >= NUMBER_OF_FROGENDS) {
			if (getScene() instanceof FroggerScene)
				((FroggerScene) getScene()).nextLevel();
		}
	}

}
