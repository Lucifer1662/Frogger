package GameEngine.GameObjects;
import java.awt.AWTEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Components.Component;
import GameEngine.Components.Transform;
import GameEngine.CoreInterfaces.IOnRootChanged;
import GameEngine.CoreInterfaces.OnCollideable;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

public class GameObject implements OnCollideable, IOnRootChanged, Updateable, Renderable {
	ArrayList<Component> components;
	private List<GameObject> children;
	private GameObject parent;
	private Transform transform;
	private Scene scene;

	private List<Updateable> updateables;
	private List<Renderable> renderables;
	private List<OnCollideable> onColliderables;
	
	public <T extends Component> T AddComponent(T component) {
		component.setGameObjectAttachedTo(this);
		if(component instanceof Updateable)
			updateables.add((Updateable)component);
		
		if(component instanceof Renderable)
			renderables.add((Renderable)component);
		
		if(component instanceof OnCollideable)
			onColliderables.add((OnCollideable)component);
		
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
		renderables = new ArrayList<Renderable>();
		updateables = new ArrayList<Updateable>();
		onColliderables = new ArrayList<OnCollideable>();
		transform = new Transform();
		transform.setGameObjectAttachedTo(this);
	}
	
	public GameObject(Scene scene, GameObject parent){
		this();
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
		for(Updateable updateable : updateables)	
			updateable.update();
	
		for(GameObject gameObject : children)	
			gameObject.update();

	}
	
	@Override
	public void onCollision(Collider collider) {
		for(OnCollideable onColliderable : onColliderables)
			onColliderable.onCollision(collider);
		
		for(GameObject gameObject : children)
			gameObject.onCollision(collider);
	}

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
	public Game getGame() {
		return getScene().getGame();
	}
	public Window getWindow() {
		return getScene().getWindow();
	}
	public Input getInput() {
		return getScene().getInput();
	}
	
	
	public void setScene(Scene scene) {
		//Changing parent to a new world root
		//is equivalent to swapping scenes
		if(scene != null)
			setParent(scene.getCamera());
		
		//Change scene,and change into scenes window
		this.scene = scene;
	}

	public static boolean canGetScene(GameObject obj) {
		return obj != null && obj.getScene() != null;
	}
	
	
	
}