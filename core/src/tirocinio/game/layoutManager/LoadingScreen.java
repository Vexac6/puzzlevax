package tirocinio.game.layoutManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class LoadingScreen extends Stage implements Screen {

    private float progress;
    private Table table;
    private ProgressBar loadingBar;
    private PuzzleVax game;

    public LoadingScreen(PuzzleVax app) {
        super( new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.game = app;
        table = new Table();
        loadingBar = new ProgressBar(0,100,1,false, new Skin(Gdx.files.internal("skin.json")));
    }

    @Override public void show () {
        game.assets.load("skin.atlas", TextureAtlas.class);
        game.assets.load("skin.json", Skin.class, new SkinLoader.SkinParameter("skin.atlas"));
        table.setFillParent(true);
        table.padLeft(getWidth() / 10).padRight(getWidth() / 10);
        table.add(loadingBar).center().expandX().fill();
        this.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        progress = MathUtils.lerp(progress, game.assets.getProgress(), .1f);
        loadingBar.setValue(progress*100);
        if (game.assets.update() && progress >= game.assets.getProgress() - .001f) {
            ScreenManager.getInstance().initialize(game);
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        }

        super.act(delta);
        super.draw();
    }

    @Override
    public void resize(int width, int height) {    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void hide() {    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
