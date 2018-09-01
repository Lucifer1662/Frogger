package FroggerGame;

import java.util.ArrayList;
import java.util.List;

import FroggerGame.Frog.Frog;
import FroggerGame.Row.FroggerRow;
import FroggerGame.Row.GrassRow;
import FroggerGame.Row.HighWayRow;
import FroggerGame.Row.WaterRow;
import GameEngine.Game;
import GameEngine.Scene;
import GameEngine.Window;


/*
 * The Main level of frogger
 */
public class MainLevel extends Scene{
	
	private static final float SCREEN_WIDTH_IN_UNITS = 21;
	private static final int NUM_OF_WATER_ROWS = 6;
	
	//offsets and separations converted from pixel to units
	private static final float OFFSETS[] = {250/48, 8/3.0f, 4/3.0f,0,1 };
	private static final float SEPERATION[] = {6.5f, 5, 12, 5, 6.5f };
	
	
	public MainLevel(Game game) {
		super(game);
		
		//Create list of rows to be used by frog
		List<FroggerRow> rows = new ArrayList<FroggerRow>();
		
		//set the camera dimensions and position
		getCamera().SetOrthographicSize(SCREEN_WIDTH_IN_UNITS);
		float aspect = (float)Window.getWindow().getWidth()/Window.getWindow().getHeight();
		getCamera().SetPoistion(0, SCREEN_WIDTH_IN_UNITS/aspect-1);
		
		//Create first grass row
		int i=0;
		FroggerRow row = new GrassRow(this,SCREEN_WIDTH_IN_UNITS);
		row.getTransform().setPosition(0, i++);
		rows.add(row);
		
		//Create highway rows
		int offsetIndex = i;
		for(int length = i + OFFSETS.length; i < length; i ++) {
			row = new HighWayRow(this, SCREEN_WIDTH_IN_UNITS,
					(i%2) == 0 ? -1: 1, OFFSETS[i-offsetIndex], SEPERATION[i-offsetIndex]);
			row.getTransform().setPosition(0,i);
			rows.add(row);
		}
		
		//Create second grass row
		row = new GrassRow(this, SCREEN_WIDTH_IN_UNITS);
		row.getTransform().setPosition(0, i++);
		rows.add(row);
		
		//Create water rows
		for(int length = i + NUM_OF_WATER_ROWS; i < length; i ++) {
			row = new WaterRow(this, SCREEN_WIDTH_IN_UNITS);
			row.getTransform().setPosition(0,i);
			rows.add(row);
		}
		
		//Create Player Frog
		Frog frog = new Frog(this, rows);
		frog.getTransform().setPosition(0,-1);		
		
	}
	
	
}
