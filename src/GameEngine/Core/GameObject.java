package GameEngine.Core;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Components.Collider;
import GameEngine.Components.Transform;
import GameEngine.CoreInterfaces.OnCollisionable;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;


/*
 * A object to host components and the update and render them
 * Also can be used as a heiarchical structure to contain child GameObjects
 * to render and update
 * The transform(position, rotation, scale) is all relative to its parent
 */

public class GameObject implements OnCollisionable, 
Updateable, Renderable {
	//List of children and parent GameObject
	private List<GameObject> children = new ArrayList<GameObject>();
	private GameObject parent;
	
	private final Transform transform = new Transform(this);
	private final Scene scene;
	
	//List of components, and their interfaces
	private final List<Component> components = new ArrayList<Component>();
	private final List<Updateable> updateables = new ArrayList<Updateable>();
	private final List<Renderable> renderables = new ArrayList<Renderable>();
	private final List<OnCollisionable> onColliderables =  new ArrayList<OnCollisionable>();
	

	public GameObject(Scene scene){
		this(scene, scene.getCamera());
	}	
	
	public GameObject(Scene scene, GameObject parent){
		this.scene = scene;
		setParent(parent);
	}
	
	public GameObject(Scene scene, GameObject parent, Vector2f pos, Vector2f scale, float angleOfRotation){
		this(scene, parent, pos.x, pos.y, scale.x, scale.y, angleOfRotation);
	}
	
	public GameObject(Scene scene, GameObject parent, float posx, float posy, float scalex, float scaley, float angleOfRotation){
		this(scene, parent);
		transform.Apply(posx, posy, scalex,scaley, angleOfRotation);
	}
	
	
	@Override
	public void render() {
		//Render the parent components first so that are on the bottom
		//of the children		
		for(Renderable renderable : renderables)	
			renderable.render();
		
		for(GameObject gameObject : children)	
			gameObject.render();
	}

	@Override
	public void update() {
		//update components then the children
		for(Updateable updateable : updateables)	
			updateable.update();
	
		for(GameObject gameObject : children)	
			gameObject.update();
	}
	
	@Override
	public void onCollision(Collider collider) {
		//tell my components a collision has happened 
		//then propagate down through children
		for(OnCollisionable onColliderable : onColliderables)
			onColliderable.onCollision(collider);
		
		for(GameObject gameObject : children)
			gameObject.onCollision(collider);
	}
	
	/*
	 * Adds a component to the gameObject	 
	 * but can only and should only be accessed by component
	 * thus default privacy
	 */
	void AddComponent(Component component) {		
		components.add(component);
		if(component instanceof Updateable)
			updateables.add((Updateable)component);
			
		if(component instanceof Renderable)
			renderables.add((Renderable)component);
			
		if(component instanceof OnCollisionable)
			onColliderables.add((OnCollisionable)component);
	}
		
	public ArrayList<GameObject> getChildren(){
		return new ArrayList<GameObject>(children);
	}
		
	public boolean removeComponent(Object component){
		if(components.remove(component)) {
			removeFromInterfaces(component);
			return true;
		}
		return false;
	}
		
	public Component removeComponent(int index){
		Component component = components.remove(index); 
		if(component != null) {
			removeFromInterfaces(component);
		}
		return component;
	}
	
	private void removeFromInterfaces(Object component) {
		if(component instanceof Updateable)
			updateables.remove((Updateable)component);
			
		if(component instanceof Renderable)
			renderables.remove((Renderable)component);
			
		if(component instanceof OnCollisionable)
			onColliderables.remove((OnCollisionable)component);
	}
		

	public Component getComponent(int index) {
		return components.get(index);
	}
			

	public void setParent(GameObject parent) {
		//Remove me from old parent
		if(this.parent != null) 
			this.parent.removeChild(this);
		
		
		//set my new parent
		this.parent = parent;
		
		//Add me to the new parent
		if(parent != null) {
			parent.children.add(this);
		}	
		
		//update my transform
		getTransform().UpdateTransformationMatrix();
	}
	
	public boolean removeChild(GameObject gameObject) {
		if(children.remove(gameObject)) {
			gameObject = null;
			return true;
		}
		return false;
	}
	
	public Transform getTransform() {
		return transform;
	}
	public Scene getScene() {
		return scene;
	}
	public Game getGame() {
		return getScene().getGame();
	}
	public GameObject getParent() {
		return parent;
	}

	public static boolean canGetScene(GameObject obj) {
		return obj != null && obj.getScene() != null;
	}

	
	
	
	
}
