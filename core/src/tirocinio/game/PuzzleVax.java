package tirocinio.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

import tirocinio.game.gameManager.Road;
import tirocinio.game.layoutManager.LoadingScreen;

public class PuzzleVax extends Game {

	public static final String TITLE = "PuzzleVax";
	public static Skin SKIN;

	public enum Puzzle_T {
		FLOW, //("Flow"),
		LOOP, //("Loop"),
		MAZE, //("Maze"),
		ROAD, //("Road"),
		BRIDGES, //("Bridges"),
		PIPE, //("Pipe"),
		SHIKAKU, //("Shikaku"),
		UNLOCK, //("Unlock"),
		CROVER, //("Crover"),
		SUMMIT, //("Summit"),
		BONUS; //("Bonus");
	}

	//private GameManager gm;
	//private Puzzle_T puzzle;
	//private MainMenu menu;
	private Stage stage;

	//public LoadingScreen loadingScreen;
	//public MainMenuScreen mainMenuScreen;
	//public GameScreen gameScreen;
	//public OrthographicCamera camera;
	public AssetManager assets;
	//public BitmapFont font;
	//public SpriteBatch batch;
	public LoadingScreen loading;

	@Override
	public void create() {

		//assets = new AssetManager();
		SKIN = new Skin(Gdx.files.internal("skin.json"));
		//loading = new LoadingScreen(this);
		//setScreen(loading);

        stage = new Stage(new FitViewport(450,800));
        Road puzzle = new Road(2);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(puzzle.getTable());

		/**

		//SKIN = new Skin(Gdx.files.internal("skin.json"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		assets = new AssetManager();
		font = new BitmapFont();
		batch = new SpriteBatch();
		loadingScreen = new LoadingScreen();
		mainMenuScreen = new MainMenuScreen();

		//stage = new Stage();
		//Gdx.input.setInputProcessor(stage);

		//gm = new GameManager();
		//menu = new MainMenu(gm);
		//puzzle = new Puzzle_T(gm,Tipo.FLOW,8);
		//gm.push(puzzle);
		//Gdx.gl.glClearColor(0.9f,0.9f,1,1);

		this.setScreen(loadingScreen);
		 **/
	}

	@Override
	public void render() {

        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
		//super.render();        //Gdx.gl.glClearColor(0.9f,0.9f,1,1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//stage.act(Gdx.graphics.getDeltaTime());
		//stage.draw();
		//gm.update(Gdx.graphics.getDeltaTime());
		//gm.render();

	}

	@Override
	public void dispose() {
		//gm.pop();
		//batch.dispose();
		//font.dispose();
		assets.dispose();
        stage.dispose();
		//loadingScreen.dispose();
		//splashScreen.dispose();
		//mainMenuScreen.dispose();
		//playScreen.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
