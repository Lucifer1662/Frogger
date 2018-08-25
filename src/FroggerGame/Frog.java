package FroggerGame;

import GameEngine.GameObject;

public class Frog extends GameObject {
	
	public Frog() {
		FrogPlayerInput input = AddComponent(new FrogPlayerInput());
		AddComponent(new FrogComponent(input));
	}
}
