package tirocinio.game.layoutManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.ScreenEnum;
import tirocinio.game.utils.ScreenManager;

public class SettingsScreen extends AbstractScreen{

    private Button back;
    private Label titolo;
    private TextButton volume, resetProg, contactMe, rateMe;
    private boolean isVolumeON; // TODO rimuovere quando metterò il volume

    public SettingsScreen (PuzzleVax app) {
        super(app);

        back = new Button(skin,"back");
        titolo = new Label("Opzioni",skin,"big");
        volume = new TextButton("Volume OFF",skin, "cool");
        resetProg = new TextButton("Azzera Progressi",skin, "cool");
        resetProg.setColor(0.8f,0.3f,0.4f,1);
        contactMe = new TextButton("Contattami!", skin, "cool");
        rateMe = new TextButton("Valuta PuzzleVax",skin, "cool");
        rateMe.setColor(0.3f,0.8f,0.5f,1);
    }

    @Override
    public void buildStage() {
        isVolumeON = true;
        volume.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = back.getOriginX();
                float by = back.getOriginY();
                float w = back.getWidth();
                float h = back.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) { // TODO VOLUME
                    if (isVolumeON) {
                        volume.setColor(0.3f, 0.3f, 0.3f, 1);
                        volume.getLabel().setText("Volume ON");
                        volume.getLabel().setColor(1, 1, 1, 1);
                        isVolumeON = false;
                    }
                    else {
                        volume.setColor(1,1,1,1);
                        volume.getLabel().setText("Volume OFF");
                        volume.getLabel().setColor(0, 0, 0.2f, 1);
                        isVolumeON = true;
                    }
                }
            }});

        resetProg.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = back.getOriginX();
                float by = back.getOriginY();
                float w = back.getWidth();
                float h = back.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) { // TODO RESET
                    return; }
            }});

        contactMe.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = back.getOriginX();
                float by = back.getOriginY();
                float w = back.getWidth();
                float h = back.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) { // TODO CONTACT
                    return; }
            }});

        rateMe.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true; }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                float bx = back.getOriginX();
                float by = back.getOriginY();
                float w = back.getWidth();
                float h = back.getHeight();
                if (x >= bx && x<= bx+w && y >= by && y <= by+h) { // TODO RATE
                    return; }
            }});

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

        table.setFillParent(true);
        table.pad(V_WIDTH/20);
        table.add(back).top();
        table.add(titolo).expandX();
        table.row();
        table.add(volume).colspan(2).center();
        table.row();
        table.add(resetProg).colspan(2).center();
        table.row();
        table.add(rateMe).colspan(2).center();
        table.row();
        table.add(contactMe).colspan(2).center();

        this.addActor(table);
    }

    @Override
    public void render(float delta) {
        if (!isVolumeON){ //TODO non funziona mai. Aspettare di mettere i suoni
            volume.setColor(0.3f, 0.3f, 0.3f, 1);
            volume.getLabel().setColor(1, 1, 1, 1);
            volume.getLabel().setText("Volume ON");
        }
        // Clear screen
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calling to Stage methods
        super.act(delta);
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}