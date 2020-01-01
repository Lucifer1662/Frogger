package GameEngine.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Components.Collider;
import GameEngine.Components.Transform;
import GameEngine.CoreInterfaces.OnCollisionable;
import GameEngine.CoreInterfaces.OnDestroy;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

/**
 * A object to host components and the update and render them. Also can be used
 * as a heiarchical structure to contain child GameObjects to render and update
 * The transform(position, rotation, scale) is all relative to its parent
 * 
 * @author Luke Hawkins
 */

public class GameObject implements OnCollisionable, Updateable, Renderable {
	// List of children and parent GameObject
	private List<GameObject> children = new ArrayList<GameObject>();
	private GameObject parent;

	private final Transform transform = new Transform(this);
	private final Scene scene;

	// List of components, and their interfaces
	private final List<Component> components = new ArrayList<Component>();
	private final List<Updateable> updateables = new ArrayList<Updateable>();
	private final List<Renderable> renderables = new ArrayList<Renderable>();
	private final List<OnCollisionable> onColliderables = new ArrayList<OnCollisionable>();
	private final List<OnDestroy> notifyOfDestroy = new ArrayList<OnDestroy>();

	/**
	 * Constructs a gameobject in a scene
	 * 
	 * @param scene The scene attached to
	 */
	public GameObject(Scene scene) {
		this(scene, scene.getCamera());
	}

	/**
	 * Constructs a GameObject in a scene attached to a parent
	 * 
	 * @param scene  The scene attached to
	 * @param parent The parent attached to
	 */
	public GameObject(Scene scene, GameObject parent) {
		this.scene = scene;
		setParent(parent);
	}

	/**
	 * Constructs a GameObject in a scene attached to a parent, at certain
	 * position, scale and angle of rotation
	 * 
	 * @param scene           The scene attached to
	 * @param parent          The parent attached to
	 * @param pos             The starting position
	 * @param scale           The starting scale
	 * @param angleOfRotation The starting angle of rotation in degrees
	 */
	public GameObject(Scene scene, GameObject parent, Vector2f pos,
			Vector2f scale, float angleOfRotation) {
		this(scene, parent, pos.x, pos.y, scale.x, scale.y, angleOfRotation);
	}

	/**
	 * Constructs a GameObject in a scene attached to a parent, at certain
	 * position, scale and angle of rotation
	 * 
	 * @param scene           The scene attached to
	 * @param parent          The parent attached to
	 * @param posx            The starting position in the x direction
	 * @param posy            The starting position in the y direction
	 * @param scalex          The starting scale in the x direction
	 * @param scaley          The starting scale in the y direction
	 * @param angleOfRotation The starting angle of rotation in degrees
	 */
	public GameObject(Scene scene, GameObject parent, float posx, float posy,
			float scalex, float scaley, float angleOfRotation) {
		this(scene, parent);
		transform.Apply(posx, posy, scalex, scaley, angleOfRotation);
	}

	/**
	 * Renders the GameObject and all its children and components
	 */
	@Override
	public void render() {
		// Render the parent components first so that are on the bottom
		// of the children
		for (int i = 0; i < renderables.size(); i++)
			renderables.get(i).render();

		for (int i = 0; i < children.size(); i++)
			children.get(i).render();
	}

	/**
	 * Updates the GameObject and all its children and components
	 */
	@Override
	public void update() {
		// update components then the children
		for (int i = 0; i < updateables.size(); i++)
			updateables.get(i).update();

		for (int i = 0; i < children.size(); i++)
			children.get(i).update();
	}

	/**
	 * Adds a component to the gameObject but can only and should only be
	 * accessed by component thus default privacy
	 * 
	 * @param component The component to be added to the GameObject
	 */
	void addComponent(Component component) {
		components.add(component);
		if (component instanceof Updateable)
			updateables.add((Updateable) component);

		if (component instanceof Renderable)
			renderables.add((Renderable) component);

		if (component instanceof OnCollisionable)
			onColliderables.add((OnCollisionable) component);
	}

	/**
	 * Gets all the child GameObjects of this GameObject
	 * 
	 * @return The list of children
	 */
	public ArrayList<GameObject> getChildren() {
		return new ArrayList<GameObject>(children);
	}

	/**
	 * Removes a component from the GameObject
	 * 
	 * @param component The component to be removed
	 * @return Whether the gameObject was found and removed
	 */
	public boolean removeComponent(Component component) {
		if (components.remove(component)) {
			removeFromInterfaces(component);
			return true;
		}
		return false;
	}

	/**
	 * Removes component at the index, index
	 * 
	 * @param index The index to remove a component at
	 * @return The Component removed
	 */
	public Component removeComponent(int index) {
		Component component = components.remove(index);
		if (component != null) {
			removeFromInterfaces(component);
		}
		return component;
	}

	private void removeFromInterfaces(Object component) {
		if (component instanceof Updateable)
			updateables.remove((Updateable) component);

		if (component instanceof Renderable)
			renderables.remove((Renderable) component);

		if (component instanceof OnCollisionable)
			onColliderables.remove((OnCollisionable) component);
	}

	/**
	 * Gets the component at the index, index
	 * 
	 * @param index The index to retrieve the component
	 * @return The component retrieved, returns null if not found
	 */
	public Component getComponent(int index) {
		return components.get(index);
	}

	/**
	 * Sets the parent and will keep the gameObject in the same world position
	 * 
	 * @param parent The parent to be attached to
	 */
	public void setParent(GameObject parent) {
		if (parent == this.parent)
			return;

		// Remove me from old parent
		if (this.parent != null)
			this.parent.removeChild(this);

		// set my new parent
		this.parent = parent;

		Vector2f dif;

		// Add me to the new parent
		if (parent != null) {
			parent.children.add(this);
			if (parent.getParent() == null)
				dif = getTransform().getWorldPosition();
			else
				dif = getTransform().getWorldPosition()
						.sub(parent.getTransform().getWorldPosition());
		} else {
			dif = getTransform().getWorldPosition();
		}

		getTransform().setPosition(dif);
		// update my transform
		getTransform().UpdateTransformationMatrix();
	}

	/**
	 * Sets the parent and will keep the gameObject in the local position
	 * 
	 * @param parent The parent to be attached to
	 */
	public void setParentLocal(GameObject parent) {
		if (parent == this.parent)
			return;

		// Remove me from old parent
		if (this.parent != null)
			this.parent.removeChild(this);

		// set my new parent
		this.parent = parent;

		// Add me to the new parent
		if (parent != null) {
			parent.children.add(this);
		}

		// update my transform
		getTransform().UpdateTransformationMatrix();
	}

	/**
	 * Removes a child from the this GameObject
	 * 
	 * @param gameObject The GameObject to be removed
	 * @return Whether it was successful at finding and removing
	 */
	public boolean removeChild(GameObject gameObject) {
		if (children.remove(gameObject)) {
			gameObject = null;
			return true;
		}
		return false;
	}

	/**
	 * Gets the transform of the GameObject
	 * 
	 * @return The transform of the GameObject
	 */
	public Transform getTransform() {
		return transform;
	}

	/**
	 * Gets the scene the GameObject is in
	 * 
	 * @return The scene the GameOject is in
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * The Game the GameObject is in
	 * 
	 * @return The Game the GameObject is in
	 */
	public Game getGame() {
		return getScene().getGame();
	}

	/**
	 * Gets the parent of the GameObject
	 * 
	 * @return The parent of the GameObject
	 */
	public GameObject getParent() {
		return parent;
	}

	/**
	 * Checks if you can get the scene of an object. Is the GameObject not null
	 * and is the scene attached the to GameObject not null
	 * 
	 * @param obj The GameObject to be compared
	 * @return Whether the scene can be gotten from the GameObject
	 */
	public static boolean canGetScene(GameObject obj) {
		return obj != null && obj.getScene() != null;
	}

	/**
	 * When a collider of the GameObject has entered a collision
	 * 
	 * @param collider The collider collided with
	 */
	@Override
	public void onCollisionEnter(Collider collider) {
		// tell my components a collision has happened
		// then propagate down through children
		for (int i = 0; i < onColliderables.size(); i++)
			onColliderables.get(i).onCollisionEnter(collider);

		for (int i = 0; i < children.size(); i++)
			children.get(i).onCollisionEnter(collider);
	}

	/**
	 * When a collider of the GameObject is colliding
	 * 
	 * @param collider The collider colliding with
	 */
	@Override
	public void onColliding(Collider collider) {
		// tell my components a collision has happened
		// then propagate down through children
		for (int i = 0; i < onColliderables.size(); i++)
			onColliderables.get(i).onColliding(collider);

		for (int i = 0; i < children.size(); i++)
			children.get(i).onColliding(collider);
	}

	/**
	 * When a collider of the GameObject has exited a collision
	 * 
	 * @param The collider that was being collided with
	 */
	@Override
	public void onCollisionExit(Collider collider) {
		// tell my components a collision has happened
		// then propagate down through children
		for (int i = 0; i < onColliderables.size(); i++)
			onColliderables.get(i).onCollisionExit(collider);

		for (int i = 0; i < children.size(); i++)
			children.get(i).onCollisionExit(collider);
	}

	/**
	 * Adds the callback to be called backed
	 * 
	 * @param notify The callback to be added
	 */
	public void addNotifyOnDestroy(OnDestroy notify) {
		notifyOfDestroy.add(notify);
	}

	/**
	 * Removes the callback to be calledback
	 * 
	 * @param notify The callback to be removed
	 */
	public void removeNotifyOnDestroy(OnDestroy notify) {
		notifyOfDestroy.remove(notify);
	}

	/**
	 * Destroys the GameObject, its components and its children and calls the
	 * onDestroy callback
	 */
	public void Destroy() {
		for (int i = 0; i < notifyOfDestroy.size(); i++)
			notifyOfDestroy.get(i).onDestroy(this);

		notifyOfDestroy.clear();
		setParent(null);
		updateables.clear();
		renderables.clear();
		onColliderables.clear();
		components.clear();
		for (GameObject child : children) {
			child.Destroy();
		}
		children.clear();
	}

}
