package tirocinio.game.gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tirocinio.game.PuzzleVax;

public abstract class Stato {

    protected GameManager gm;
    protected OrthographicCamera cam;
    protected Vector3 touch;
    protected Stage stage;
    protected Viewport viewport;

    protected Stato(GameManager gm) {
        this.gm = gm;
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        /**
        cam = new OrthographicCamera();

        int camW = Gdx.graphics.getWidth();
        int camH = Gdx.graphics.getHeight();

        cam.setToOrtho(false, camW, camH);
        touch = new Vector3(); **/
    }

    public abstract void update(float dt);
    public abstract void render();

}
