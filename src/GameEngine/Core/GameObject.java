package GameEngine.Core;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;
import GameEngine.Components.Collider;
import GameEngine.Components.Transform;
import GameEngine.CoreInterfaces.IOnRootChanged;
import GameEngine.CoreInterfaces.Initializable;
import GameEngine.CoreInterfaces.OnCollideable;
import GameEngine.CoreInterfaces.Renderable;
import GameEngine.CoreInterfaces.Updateable;

public class GameObject implements OnCollideable, 
Updateable, Renderable, Initializable {
	ArrayList<Component> components;
	private List<GameObject> children;
	private GameObject parent;
	private Transform transform;
	private Scene scene;

	private List<Updateable> updateables;
	private List<Renderable> renderables;
	private List<OnCollideable> onColliderables;
	private List<Initializable> initializables;
	
	void AddComponent(Component component) {
		if(component instanceof Transform)
			return;
	
		components.add(component);
		if(component instanceof Updateable)
			updateables.add((Updateable)component);
		
		if(component instanceof Renderable)
			renderables.add((Renderable)component);
		
		if(component instanceof OnCollideable)
			onColliderables.add((OnCollideable)component);
		
		if(component instanceof Initializable)
			initializables.add((Initializable)component);
		
	}
	
	public ArrayList<GameObject> getChildren(){
		return new ArrayList<GameObject>(children);
	}
	
	public boolean removeComponent(Object component){
		if(components.remove(component)) {
			RemoveFromInterfaces(component);
			return true;
		}
		return false;
	}
	
	public Component removeComponent(int index){
		Component component = components.remove(index); 
		if(component != null) {
			RemoveFromInterfaces(component);
		}
		return component;
	}
	
	private void RemoveFromInterfaces(Object component) {
		if(component instanceof Updateable)
			updateables.remove((Updateable)component);
		
		if(component instanceof Renderable)
			renderables.remove((Renderable)component);
		
		if(component instanceof OnCollideable)
			onColliderables.remove((OnCollideable)component);
		
		if(component instanceof Initializable)
			initializables.remove((Initializable)component);
	}
	

	public Component getComponent(int index) {
		return components.get(index);
	}
	
	private GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<Component>();
		renderables = new ArrayList<Renderable>();
		updateables = new ArrayList<Updateable>();
		onColliderables = new ArrayList<OnCollideable>();
		initializables = new ArrayList<Initializable>();
		transform = new Transform(this);
	}

	public GameObject(Scene scene){
		this();
		this.scene = scene;
		setParent(scene.getCamera());
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
	public void init() {
		for(Initializable initializable : initializables)	
			initializable.init();
	
		for(GameObject gameObject : children)	
			gameObject.init();
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
	

	public static boolean canGetScene(GameObject obj) {
		return obj != null && obj.getScene() != null;
	}

	
	
	
	
}
