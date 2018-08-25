package FroggerGame;

import org.newdawn.slick.SlickException;

import GameEngine.Game;
import GameEngine.GameObject;
import GameEngine.Window;

public class FroggerGame {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			Game g = new Game("Frogger Game");
			
			
			Window window = new Window(g);
			window.setDisplayMode(600, 600, false);
			window.setVSync(true);
			
			
			
			//---------Main LvL--------
			MainLevelScene mainLevel = new MainLevelScene(g);
			GameObject frog = mainLevel.Instantiate(0,-4.5f);
			frog.AddComponent(Frog::new);
			frog.AddComponent(FrogPlayerInput::new);
			
			
			//-------End Game------
			g.SetCurrentScene(mainLevel);		
			window.start();
			
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
