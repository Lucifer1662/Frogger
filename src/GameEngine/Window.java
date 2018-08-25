package GameEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;

public class Window extends AppGameContainer {
	private Game game;
	public Window(Game game) throws SlickException {
		super(game);
		this.game = game;
		game.setWidow(this);
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

}
