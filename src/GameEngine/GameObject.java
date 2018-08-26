package GameEngine;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Collision.Collider;
import GameEngine.Collision.ICollision;

public class GameObject implements ICollision, IOnRootChanged {
	ArrayList<Component> components;
	private ArrayList<GameObject> children;
	private GameObject parent;
	private Transform transform;
	private Scene scene;
	private Window window;

	
	public <T extends Component> T AddComponent(T component) {
		components.add(component);
		component.setGameObjectAttachedTo(this);
		return component;
	}
	
	public ArrayList<GameObject> getChildren(){
		return new ArrayList<GameObject>(children);
	}
	
	public boolean removeComponent(Object component){
		return components.remove(component);
	}
	
	public Component removeComponent(int index){
		return components.remove(index);
	}
	

	public Component getComponent(int index) {
		return components.get(index);
	}
	
	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<Component>();
		transform = this.AddComponent(new Transform());
	}
	
	public GameObject(Scene scene, GameObject parent){
		this();
		window = scene.getGame().getWidow();
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

	
	
	public final void _render() throws SlickException {
		for(Component component : components)	
			component.render();
		
		for(GameObject gameObject : children)	
			gameObject._render();

		render();
	}


	public final void _update() throws SlickException {
		for(Component component : components)	
			component.update();

		for(GameObject gameObject : children)	
			gameObject._update();

		update();
	}
	
	@Override
	public void onCollision(Collider collider) {
		for(Component component : components)
			component.onCollision(collider);
		
		for(GameObject gameObject : children)
			gameObject.onCollision(collider);
	}
	
	public void render() throws SlickException {}
	public void update() throws SlickException {}

	public GameObject getParent() {
		return parent;
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
		getTransform().UpdateTransformationMatrix();
		onRootChanged(parent);
	}
	
	@Override
	public void onRootChanged(GameObject root) {
		for(Component component : components)
			component.onRootChanged(root);
			
		for(GameObject gameObject : children)
			gameObject.onRootChanged(root);
	}
	
	public boolean removeChild(GameObject gameObject) {
		return children.remove(gameObject);
	}
	
	public Transform getTransform() {
		return transform;
	}
	public Scene getScene() {
		return scene;
	}
	public Window getWindow() {
		return window;
	}
	
	public void setScene(Scene scene) {
		//Changing parent to a new world root
		//is equivalent to swapping scenes
		if(scene != null)
			setParent(scene.getCamera());
		
		//Change scene,and change into scenes window
		this.scene = scene;
		this.window = scene.getGame().getWidow();
	}

	public static boolean canGetScene(GameObject obj) {
		return obj != null && obj.getScene() != null;
	}
	
	
	
}
