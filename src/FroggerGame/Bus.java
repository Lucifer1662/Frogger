package FroggerGame;

import GameEngine.GameObject;

public class Bus extends GameObject {
	
	public Bus() {
		AddComponent(new BusSprite());
	}
}
