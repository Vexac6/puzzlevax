package tirocinio.game.layoutManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import tirocinio.game.PuzzleVax;

public abstract class AbstractScreen extends Stage implements Screen {

    public static final int P_SIZE = 350;
    public static final int HUD_H = 100;
    public static int V_WIDTH, V_HEIGHT;
    static {
        float ratio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        if (ratio < 1.45) {V_WIDTH = 450; V_HEIGHT = 600;} // 4:3 --> Tablet
        else if (ratio < 1.52) {V_WIDTH = 500; V_HEIGHT = 750;} // 15:10
        else if (ratio < 1.59) {V_WIDTH = 450; V_HEIGHT = 700;} // 14:9 (raro)
        else if (ratio < 1.65) {V_WIDTH = 500; V_HEIGHT = 800;} // 16:10
        else if (ratio < 1.70) {V_WIDTH = 450; V_HEIGHT = 750;} // 15:9
        else {V_WIDTH = 450; V_HEIGHT = 800;} // 16:9
    }
    protected Table table;
    protected Skin skin;
    protected PuzzleVax game;

    protected AbstractScreen(PuzzleVax app) {
        super( new FitViewport(V_WIDTH, V_HEIGHT));
        this.game = app;
        skin = game.assets.get("skin.json", Skin.class);
        table = new Table(skin);
    }

    // Subclasses must load actors in this method
    public abstract void buildStage();

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calling to Stage methods
        super.act(delta);
        super.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height);
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}