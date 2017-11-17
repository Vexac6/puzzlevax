package tirocinio.game.gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.Blocco;
import tirocinio.game.utils.IntMapSerializer;

public class Flow extends Puzzle {

    private HashMap<String, Color> map;

    public Flow (int lv) {
        tipo = PuzzleVax.Puzzle_T.FLOW;
        tutorial = createTutorial();
        createMap();
        loadLevel(lv);
        createBoard();
    }

    @Override
    public void createMap() {
        List<Color> colori = new LinkedList<Color>();
        colori.add(Color.CORAL);
        colori.add(Color.CYAN);
        colori.add(Color.DARK_GRAY);
        colori.add(Color.FOREST);
        colori.add(Color.GOLD);
        colori.add(Color.LIME);
        colori.add(Color.LIGHT_GRAY);
        colori.add(Color.MAROON);
        colori.add(Color.ORANGE);
        colori.add(Color.PURPLE);
        colori.add(Color.SCARLET);
        colori.add(Color.TEAL);

        Color[] randoms = new Color[colori.size()];
        for (int i=0; i<randoms.length; i++) {
            int rnd = new Random().nextInt(colori.size());
            randoms[i] = colori.get(rnd);
            colori.remove(rnd);
        }

        map = new HashMap<String, Color>();
        map.put("A", randoms[0]);
        map.put("B", randoms[1]);
        map.put("C", randoms[2]);
        map.put("D", randoms[3]);
        map.put("E", randoms[4]);
        map.put("F", randoms[5]);
        map.put("G", randoms[6]);
        map.put("H", randoms[7]);
        map.put("I", randoms[8]);
        map.put("J", randoms[9]);
        map.put("K", randoms[10]);
        map.put("L", randoms[11]);
    }

    @Override
    public void initialize(Queue<String> tiles, String[] coords) {

        for (String coord : coords)
            posizioni.add(Integer.parseInt(coord));

        for (String key:  tiles) {
            final Image img = new Image(PuzzleVax.SKIN, tipo + "_" + key);
            if ("ABCEHVX".contains(key)) {
                img.addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }

                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {}
                });
            }
            blocchi.add(img);
        }
    }

    @Override
    public String createTutorial() {return tutorial;}
}
