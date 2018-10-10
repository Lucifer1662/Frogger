package GameEngine.Core;

import GameEngine.Scene;

/**
 * A user interface object to host/render user interfaces
 * 
 * @author lhawk
 *
 */
public class UserInterfaceObject extends GameObject {

	/**
	 * Constructs a UserInterfaceObject and adds it to the scene and its parent
	 * 
	 * @param scene  The scene to be attached to
	 * @param parent The parent to be attached to
	 */
	public UserInterfaceObject(Scene scene, GameObject parent) {
		super(scene, parent);
	}

	/**
	 * Constructs a UserInterfaceObjectn and adds it to the scene with its parent
	 * being the world
	 * 
	 * @param scene The scene to be attached to the UserInterfaceObject
	 */
	public UserInterfaceObject(Scene scene) {
		super(scene, scene.getCamera().getUserInterface());
	}
}
