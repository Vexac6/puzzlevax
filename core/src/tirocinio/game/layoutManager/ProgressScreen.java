package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class ProgressScreen extends AbstractScreen{

    private Button back;
    private ProgressBar[] progressi;
    private Label titolo;
    private ScrollPane scrollPane;

    public ProgressScreen (PuzzleVax app) {
        super(app);
        back = new Button(skin,"back");
        titolo = new Label("Progressi",skin,"big");
        progressi = new ProgressBar[15];
        for (int i=0; i<progressi.length; i++) {
            progressi[i] = new ProgressBar(0, 100, 1, false, skin);
            progressi[i].setValue(50); // caricare il progresso corrente da file
        }
        scrollPane = new ScrollPane(table,skin);
    }

    @Override
    public void buildStage() {
        scrollPane.setDebug(true);
        scrollPane.setClamp(true);
        scrollPane.setBounds(30, 30, V_WIDTH-60, V_HEIGHT-200);
        for (int i=0; i<progressi.length; i++) {
            table.add("progresso "+i+": ");
            table.add(progressi[i]).expandX().fillX().padRight(V_WIDTH/20);
            table.row();
        }

        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = back.getOriginX();
                float by = back.getOriginY();
                float w = back.getWidth();
                float h = back.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                    ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            }});

        back.setPosition(V_WIDTH/20, V_HEIGHT - 150);
        titolo.setPosition(160,V_HEIGHT - 150);

        this.addActor(back);
        this.addActor(titolo);
        this.addActor(scrollPane);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
