package GameEngine;
import java.util.ArrayList;
import java.util.function.Supplier;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class GameObject {
	ArrayList<Component> components;
	private ArrayList<GameObject> children;
	private GameObject parent;
	private Transform transform;
	private Scene scene;
	private Window window;
	
	public <T extends Component> T AddComponent(Supplier<T> s) {
		T component = s.get();
		components.add(component);
		component.SetGameObjectAttachedTo(this);
		return component;
	}
	
	public ArrayList<GameObject> getChildren(){
		return new ArrayList<GameObject>(children);
	}
	
	public boolean RemoveComponent(Object component){
		return components.remove(component);
	}
	
	public Component RemoveComponent(int index){
	
		return components.remove(index);
	}
	

	public Component GetComponent(int index) {
		return components.get(index);
	}
	
	public GameObject(Scene scene, GameObject parent){
		window = scene.getGame().getWidow();
		this.scene = scene;
		children = new ArrayList<GameObject>();
		components = new ArrayList<Component>();
		transform = this.AddComponent(Transform::new);
		setParent(parent);
	}	
	
	public GameObject(Scene scene, GameObject parent, Vector2f pos, Vector2f scale, float angleOfRotation){
		this(scene, parent, pos.x, pos.y, scale.x, scale.y, angleOfRotation);
	}
	
	public GameObject(Scene scene, GameObject parent, float posx, float posy, float scalex, float scaley, float angleOfRotation){
		this(scene, parent);
		transform.Apply(posx, posy, scalex,scaley, angleOfRotation);
	}

	
	
	public void Render(Window window, Graphics graphics) throws SlickException {
		for(Component component : components)	{
			component.render(window, graphics);
		}
		for(GameObject gameObject : children)	{
			gameObject.Render(window, graphics);
		}
		render(window, graphics);
	}

	public void Init(Window window) throws SlickException {
		for(Component component : components)	{
			component.init(window);
		}
		for(GameObject gameObject : children)	{
			gameObject.Init(window);
		}
		init(window);
	}

	public void Update(Window window) throws SlickException {
		for(Component component : components)	{
			component.update(window);
		}
		for(GameObject gameObject : children)	{
			gameObject.Update(window);
		}
		update(window);
	}
	
	public void render(Window window, Graphics graphics) throws SlickException {}
	public void init(Window window) throws SlickException {}
	public void update(Window window) throws SlickException {}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		//Add me to the new parent
		if(parent != null)
			parent.children.add(this);
		
		//Remove me from old parent
		if(this.parent != null) 
			this.parent.removeChild(this);
		
		//set my new parent
		this.parent = parent;
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
		//Setting parent to null is equivalent to removing seen
		//as it is the only thing tying me here
		setParent(null);
		
		//Change scene,and change into scenes window
		this.scene = scene;
		this.window = scene.getGame().getWidow();
	}
	
	
	
}
