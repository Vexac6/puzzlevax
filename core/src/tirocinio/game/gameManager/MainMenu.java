package tirocinio.game.gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import tirocinio.game.PuzzleVax;


public class MainMenu extends Stato {

    private Table tab, tab2;
    private Button settings, tutorial, education, progress, back, left, right, logo;
    private TextButton sample;
    private Container<Button> c1, c2, c3, c4, c5, c6, c7, c8, c9;

    private final float WIDTH = Gdx.graphics.getWidth();
    private final float HEIGHT = Gdx.graphics.getHeight();
    private final float HUD = (HEIGHT) / 5;
    private final float SIZE = (HEIGHT) / 2;
    private final float pad = (WIDTH - SIZE) / 2;
    private final float buttonsize = (WIDTH - HUD - pad) / 2;

    public MainMenu(GameManager gm) {
        super(gm);

        Image BGTop = new Image(PuzzleVax.SKIN, "HUD_BG");
        Image BGBottom = new Image(PuzzleVax.SKIN, "HUD_BG");
        Button emptySpot = settings;

        tab = new Table();
        tab2 = new Table();

        settings = new Button(PuzzleVax.SKIN, "settings");
        logo = new Button(PuzzleVax.SKIN, "logo");
        tutorial = new Button(PuzzleVax.SKIN, "tutorial");
        education = new Button(PuzzleVax.SKIN, "education");
        back = new Button(PuzzleVax.SKIN, "back");
        progress = new Button(PuzzleVax.SKIN, "progress");
        left = new Button(PuzzleVax.SKIN, "left");
        right = new Button(PuzzleVax.SKIN, "right");
        sample = new TextButton("PROVA", PuzzleVax.SKIN, "sample");

        c1 = new Container<Button>(settings);
        c1.pad(pad);
        //c2 = new Container<Button>(logo);
        c3 = new Container<Button>(tutorial);
        c3.pad(pad);
        c4 = new Container<Button>(education);
        c4.pad(pad);
        c5 = new Container<Button>(back);
        c5.pad(pad);
        c6 = new Container<Button>(progress);
        c6.pad(pad);
        c7 = new Container<Button>(left);
        c8 = new Container<Button>(right);
        c9 = new Container<Button>(sample);


        tab.setFillParent(true);
        tab.setDebug(true);
        tab.padLeft(pad).padRight(pad);

        tab.add(c1).size(buttonsize).padBottom(pad);
        tab.add(logo).size(HUD);
        tab.add(c3).size(buttonsize).padBottom(pad);
        tab.row().height(SIZE);
        tab.add(emptySpot).expand().colspan(3).padTop(pad).padBottom(pad);
        tab.row();
        tab.add(c4).size(buttonsize).padTop(pad);
        tab.add(c5).size(buttonsize).padTop(pad);
        tab.add(c6).size(buttonsize).padTop(pad);

        tab2.setFillParent(true);
        //tab2.setDebug(true);
        tab2.center();
        tab2.add(left);
        tab2.add(sample).expandX();
        tab2.add(right);

        BGBottom.setBounds(0, 0, WIDTH, HUD - pad);
        BGTop.setBounds(0, HEIGHT - HUD + pad, WIDTH, HUD - pad);
        stage.addActor(BGBottom);
        stage.addActor(BGTop);
        stage.addActor(tab);
        stage.addActor(tab2);
        Gdx.input.setInputProcessor(stage);

        settings.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tocco a (" + x + "," + y + ")");
                System.out.println("Pannello Opzioni");
                return false;
            }
        });

        tutorial.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tocco a (" + x + "," + y + ")");
                System.out.println("Dialog di istruzioni del gioco");
                return false;
            }
        });

        education.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tocco a (" + x + "," + y + ")");
                System.out.println("Pannello Educational");
                return false;
            }
        });

        progress.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tocco a (" + x + "," + y + ")");
                System.out.println("Pannello Progressi");
                //display(lv);
                return false;
            }
        });

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}