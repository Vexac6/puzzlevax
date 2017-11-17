package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import tirocinio.game.PuzzleVax;
import tirocinio.game.gameManager.Puzzle;
import tirocinio.game.gameManager.Road;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class GameScreen extends AbstractScreen {

    private HUD up,down;
    private Button home, tutorial, moveBack, refresh, displayPuzzle, displayLevel;
    //private TextButton displayPuzzle, displayLevel;
    private Puzzle puzzle;

    public GameScreen (PuzzleVax app, PuzzleVax.Puzzle_T tipo, int lv) {
        super(app);
        home = new Button(skin, "home");
        tutorial = new Button(skin, "tutorial");
        moveBack = new Button(skin, "moveBack");
        refresh = new Button(skin, "refresh");
        /**
        displayPuzzle = new TextButton(PuzzleVax.Puzzle_T.values()[id].name(),skin,"displayPuzzle");
        displayLevel = new TextButton(Integer.toString(lv+1),skin,"displayLevel");
         **/
        displayPuzzle = new Button(skin, "education"); //TODO
        displayLevel = new Button(skin, "settings"); //TODO
        up = new HUD(skin, home, displayPuzzle, tutorial);
        down = new HUD(skin, moveBack, displayLevel, refresh);

        initPuzzle(tipo,lv);
    }

    @Override
    public void buildStage() {

        table.setFillParent(true);
        table.add(up).expandX().fill();
        table.row();
        table.add(puzzle.getTable()).expand();
        table.row();
        table.add(down).expandX().fill();

        home.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = home.getOriginX();
                float by = home.getOriginY();
                float w = home.getWidth();
                float h = home.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                    // TODO DIALOG
                    ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
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

        moveBack.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = moveBack.getOriginX();
                float by = moveBack.getOriginY();
                float w = moveBack.getWidth();
                float h = moveBack.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                    return;
            }});

        refresh.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = refresh.getOriginX();
                float by = refresh.getOriginY();
                float w = refresh.getWidth();
                float h = refresh.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) {
                    return;
                }
            }});

        this.addActor(table);
    }

    @Override
    public void dispose() { super.dispose();}

    private void initPuzzle(PuzzleVax.Puzzle_T tipo, int lv) { //TODO
        switch (tipo) {
            case FLOW: {}//puzzle = new Flow(lv, skin);
            case LOOP: {}//puzzle = new Loop(lv, skin);
            case MAZE: {}//puzzle = new Maze(lv, skin);
            case ROAD: puzzle = new Road(lv, skin);
            case BRIDGES: {}//puzzle = new Bridges(lv, skin);
            case PIPE: {}//puzzle = new Pipe(lv, skin);
            case SHIKAKU: {}//puzzle = new Shikaku(lv, skin);
            case UNLOCK: {}// puzzle = new Unlock(lv, skin);
            case CROVER: {}//puzzle = new Crover(lv, skin);
            case SUMMIT: {}//puzzle = new Summit(lv, skin);
            case BONUS: {}// puzzle = new Bonus(lv, skin);
        }
    }
}
