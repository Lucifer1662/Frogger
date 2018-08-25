package GameEngine;

public class SceneLoadedFromFile extends Scene {
	@SuppressWarnings("unused")
	private String sceneFilePath;
		
	public SceneLoadedFromFile(Game game, String sceneFilePath) {
		super(game);
		this.sceneFilePath = sceneFilePath;
	}
	
	public void LoadScene() {
		
	}
	
	@Override
	public void init(Window window) {
		LoadScene();
	}
}
