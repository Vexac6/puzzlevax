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
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class PuzzleVax extends Game {

	public static final String TITLE = "PuzzleVax";
	public static Skin SKIN;

	public enum Puzzle_T {
		FLOW,
		LOOP,
		MAZE,
		ROAD,
		BRIDGES,
		PIPE,
		SHIKAKU,
		UNLOCK,
		CROVER,
		SUMMIT,
		BONUS
	}

	//private GameManager gm;
	//private Puzzle_T puzzle;
	//private MainMenu menu;
	//private Stage stage;

	//public MainMenuScreen mainMenuScreen;
	//public GameScreen gameScreen;
	//public OrthographicCamera camera;
	public AssetManager assets;
	//public BitmapFont font;
	//public SpriteBatch batch;
	public LoadingScreen loading;

	@Override
	public void create() {

		//SKIN = new Skin(Gdx.files.internal("skin.json"));
		assets = new AssetManager();
		loading = new LoadingScreen(this);
		setScreen(loading);

        //stage = new Stage(new FitViewport(450,800));
        //Road puzzle = new Road(1);
        //Gdx.input.setInputProcessor(stage);
        //stage.addActor(puzzle.getTable());
	}

	@Override
	public void render() {

        //Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //stage.act(Gdx.graphics.getDeltaTime());
        //stage.draw();
		super.render();
		//Gdx.gl.glClearColor(0.9f,0.9f,1,1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//stage.act(Gdx.graphics.getDeltaTime());
		//stage.draw();
		//gm.update(Gdx.graphics.getDeltaTime());
		//gm.render();

	}

	@Override
	public void dispose() {
		//SKIN.dispose();
		assets.dispose();
        //stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
