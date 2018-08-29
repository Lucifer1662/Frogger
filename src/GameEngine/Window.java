package GameEngine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import GameEngine.Game;

public class Window extends AppGameContainer {
	private Game game;
	public Window(Game game) throws SlickException {
		super(game);
		this.game = game;
		game.setWindow(this);
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public float getPixelToUnit() {
		return game.getCurrentScene().getCamera().getOrthographicSize()/getWidth();
	}
	public float getUnitToPixels() {
		return getWidth()/game.getCurrentScene().getCamera().getOrthographicSize();
	}

}
