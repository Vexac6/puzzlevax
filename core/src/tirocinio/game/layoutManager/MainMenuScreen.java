package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

import static tirocinio.game.utils.ScreenEnum.LEVELS;

public class MainMenuScreen extends AbstractScreen {

    private HUD up, down;
    private Button settings, tutorial, education, progress, arrowUp, arrowDown;
    private TextButton[] snapshot;
    private Stack stack;
    private int visibile;

    final float PAD = V_WIDTH / 20;

    public MainMenuScreen(PuzzleVax app) {
        super(app);
        settings = new Button(skin,"settings");
        tutorial = new Button(skin,"tutorial");
        education = new Button(skin, "education");
        progress = new Button(skin, "progress");
        arrowUp = new Button(skin, "up");
        arrowDown = new Button(skin, "down");
        up = new HUD(skin, settings, tutorial);
        down = new HUD(skin, education, progress);
        stack = new Stack();
        snapshot = new TextButton[PuzzleVax.Puzzle_T.values().length];
    }

    @Override
    public void buildStage() {
        table.setFillParent(true);
        table.add(up).expandX().fill();
        table.row();
        table.add(arrowUp).expandX().padTop(8);
        table.row();
        table.add(stack).expand();
        table.row();
        table.add(arrowDown).expandX().padBottom(8);
        table.row();
        table.add(down).expandX().fill();

        visibile = 0;
        for (int i=0; i<snapshot.length; i++) {
            final int index = i;
            snapshot[i] = new TextButton(PuzzleVax.Puzzle_T.values()[i].name(), skin, "snapshot");
            snapshot[i].setBounds(V_WIDTH/8, V_HEIGHT/2 - P_SIZE/2, P_SIZE, P_SIZE);
            if (i != visibile) snapshot[i].setVisible(false);
            stack.add(snapshot[i]);
            snapshot[i].addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true; }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    float bx = stack.getOriginX();
                    float by = stack.getOriginY();
                    float w = stack.getWidth();
                    float h = stack.getHeight();
                    if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                        ScreenManager.getInstance().showScreen(LEVELS, index);
                }});
        }

        settings.addListener(new InputListener() { // TODO ChangeListener
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = settings.getOriginX();
                float by = settings.getOriginY();
                float w = settings.getWidth();
                float h = settings.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                    ScreenManager.getInstance().showScreen(ScreenEnum.SETTINGS);
            }});

        progress.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = progress.getOriginX();
                float by = progress.getOriginY();
                float w = progress.getWidth();
                float h = progress.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                    ScreenManager.getInstance().showScreen(ScreenEnum.PROGRESS);
            }});

        arrowUp.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                float bx = arrowUp.getOriginX();
                float by = arrowUp.getOriginY();
                float w = arrowUp.getWidth();
                float h = arrowUp.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) {

                    if (visibile == snapshot.length-1) {
                        snapshot[visibile].setVisible(false);
                        snapshot[0].setVisible(true);
                        visibile = 0;
                    }
                    else {
                        snapshot[visibile].setVisible(false);
                        snapshot[visibile+1].setVisible(true);
                        visibile++;
                    }
                }
                return false;
            }});

        arrowDown.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                float bx = arrowDown.getOriginX();
                float by = arrowDown.getOriginY();
                float w = arrowDown.getWidth();
                float h = arrowDown.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) {

                    if (visibile == 0) {
                        snapshot[visibile].setVisible(false);
                        snapshot[snapshot.length-1].setVisible(true);
                        visibile = snapshot.length-1;
                    }
                    else {
                        snapshot[visibile].setVisible(false);
                        snapshot[visibile-1].setVisible(true);
                        visibile--;
                    }
                }
                return false;
            }});

        education.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = education.getOriginX();
                float by = education.getOriginY();
                float w = education.getWidth();
                float h = education.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) {
                    return;
                }
            }});

        tutorial.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = tutorial.getOriginX();
                float by = tutorial.getOriginY();
                float w = tutorial.getWidth();
                float h = tutorial.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) {
                    return;
                }
            }});

        this.addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}