package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class LevelScreen extends AbstractScreen {

    private HUD up;
    private Button back, tutorial;
    private ScrollPane scrollPane;
    private Table tab;
    private TextButton[] livelli;
    private Label titolo;
    private final PuzzleVax.Puzzle_T tipo;

    public LevelScreen (PuzzleVax app, PuzzleVax.Puzzle_T tipo) {
        super(app);
        this.tipo = tipo;
        back = new Button(skin, "back");
        tutorial = new Button(skin, "tutorial");
        up = new HUD(skin, back, tutorial);
        tab = new Table();
        titolo = new Label(tipo.name(), skin, "big");
        livelli = new TextButton[50];
        scrollPane = new ScrollPane(tab, skin);
    }

    @Override
    public void buildStage() {
        tab.setWidth(350); // TODO rimuovere i magic numbers una volta fissati
        for (int i=0; i<livelli.length; i++) {
            // TODO if livello is disponibile
            final int index = i;
            livelli[i] = new TextButton(Integer.toString(i + 1), skin, "levelButton");
            livelli[i].addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true; }
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    float bx = livelli[index].getOriginX();
                    float by = livelli[index].getOriginY();
                    float w = livelli[index].getWidth();
                    float h = livelli[index].getHeight();
                    if (x >= bx && x<= bx+w && y >= by && y <= by+h)
                        ScreenManager.getInstance().showScreen(ScreenEnum.GAME,tipo,index);
                }});
            if ((i+1)%5 == 0) {
                tab.add(livelli[i-4]).padLeft(11).padRight(2);
                tab.add(livelli[i-3]).padRight(2);
                tab.add(livelli[i-2]).padRight(2);
                tab.add(livelli[i-1]).padRight(2);
                tab.add(livelli[i]).padRight(13);
                tab.row();
            }
        }

        scrollPane.setDebug(true);
        scrollPane.setClamp(true);

        table.setFillParent(true);
        table.setDebug(true);
        table.add(up).expandX().fill();
        table.row();
        table.add(scrollPane).expand();
        table.row();
        table.add(titolo).expandX();

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
    public void dispose() { super.dispose();}
}
