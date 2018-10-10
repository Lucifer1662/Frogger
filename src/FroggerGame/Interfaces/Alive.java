package FroggerGame.Interfaces;

/**
 * An interface for if something is alive and can be killed
 * 
 * @author lhawk
 *
 */
public interface Alive {
	public void addLife();

	public default void addLife(int amount) {
		for (int i = 0; i < amount; i++)
			addLife();
	}

	public void removeLife();

	public default void removeLife(int amount) {
		for (int i = 0; i < amount; i++)
			removeLife();
	}

}
