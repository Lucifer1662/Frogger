package GameEngine.GameObjects;

import org.newdawn.slick.geom.Vector2f;

import GameEngine.Scene;
import GameEngine.Window;

public class Camera extends GameObject {
	private float orthographicSize = 10;
	public Camera(Scene scene, GameObject parent) {
		super(scene, parent);
	
		//Transform world root so the origin is in the middle and 
		//screen is orthographicSize in units across in the width
		float widthOrto = getWindow().getWidth()/orthographicSize;
		getTransform().Apply( getWindow().getWidth()/2.0f, getWindow().getHeight()/2.0f,
					widthOrto, -widthOrto,0.0f);
	}

	
	public void SetPoistion(float x, float y) {
		int width = getWindow().getWidth();
		int height = getWindow().getHeight();
		getTransform().setPosition(width/2.0f + x* orthographicSize,height/2.0f + y*orthographicSize);
	}
	
	public void SetPoistion(Vector2f pos) {
		SetPoistion(pos.x, pos.y);
	}
	
	public final void SetOrthographicSize(Window window, float size) {
		orthographicSize = size;
		float scale = window.getWidth()/size;
		getTransform().setScale(scale, -scale);
	}
	
}
