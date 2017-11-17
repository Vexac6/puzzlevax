package tirocinio.game.gameManager;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

import java.util.HashMap;
import java.util.Map;

public class Cella<T extends Button> extends Cell{

    private static final int MAX_SIZE = 12;
    private static Map<Integer, String> dict;
    static {
        dict = new HashMap<Integer, String>();
        for (int i=0; i<MAX_SIZE; i++)
            dict.put(i, Character.toString ((char) (i+65))); // 0 -> A, 1 -> B ... 12 -> M
    }

    private String r, c;
    private String pos;


    public Cella (int row, int col) {
        super();
        if (row>MAX_SIZE || col>MAX_SIZE)
            throw new IllegalArgumentException("Riga e Colonna non possono superare "+MAX_SIZE);
        r = dict.get(row);
        c = Integer.toString(col);
        pos = r+c;
    }

    public Cella (int size) {
        super();
        if (size>MAX_SIZE || size>MAX_SIZE)
            throw new IllegalArgumentException("Riga e Colonna non possono superare "+MAX_SIZE);
        r = dict.get(size);
        c = Integer.toString(size);
        pos = r+c;
    }

    public String r() {return r;}
    public String c() {return c;}
    public String pos() {return pos;}
}
