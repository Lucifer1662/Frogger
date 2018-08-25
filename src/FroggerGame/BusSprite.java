package FroggerGame;

import org.newdawn.slick.Image;

import GameEngine.Sprite;

public class BusSprite extends Sprite {
	private static final String busImageLocation = "assets/bus.png";

	
	public BusSprite() {
		super.setImage(busImageLocation);
	}
	
	@Override 
	public void setImage(Image image){}
}
