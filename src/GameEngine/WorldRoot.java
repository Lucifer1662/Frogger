package GameEngine;

import org.newdawn.slick.SlickException;

public class WorldRoot extends GameObject {
	private float orthographicSize = 10;
	public WorldRoot(Scene scene, GameObject parent) {
		super(scene, parent);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void init(Window window) throws SlickException {
		//Transform world root so the origin is in the middle and 
				//screen is orthographicSize in units across in the width
		float widthOrto = window.getWidth()/orthographicSize;
		getTransform().Apply(window.getWidth()/2.0f,window.getHeight()/2.0f,
					widthOrto, -widthOrto,0.0f);
	}
	

	public final void SetOrthographicSize(Window window, float size) {
		orthographicSize = size;
		float scale = window.getWidth()/size;
		getTransform().setScale(scale, scale);
	}
	
}
