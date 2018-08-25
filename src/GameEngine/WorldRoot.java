package GameEngine;


public class WorldRoot extends GameObject {
	private float orthographicSize = 10;
	public WorldRoot(Scene scene, GameObject parent) {
		super(scene, parent);
	
		//Transform world root so the origin is in the middle and 
				//screen is orthographicSize in units across in the width
		float widthOrto = getWindow().getWidth()/orthographicSize;
		getTransform().Apply( getWindow().getWidth()/2.0f, getWindow().getHeight()/2.0f,
					widthOrto, -widthOrto,0.0f);
	}
	

	public final void SetOrthographicSize(Window window, float size) {
		orthographicSize = size;
		float scale = window.getWidth()/size;
		getTransform().setScale(scale, scale);
	}
	
}
