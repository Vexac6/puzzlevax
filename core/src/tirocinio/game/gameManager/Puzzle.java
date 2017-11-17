package tirocinio.game.gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import tirocinio.game.PuzzleVax;
import tirocinio.game.layoutManager.AbstractScreen;
import tirocinio.game.utils.Blocco;
import tirocinio.game.utils.IntMapSerializer;
import tirocinio.game.utils.VirtualTile;

public abstract class Puzzle {

    protected enum POSIZIONE {
        NORD(0,1),
        EST(1,0),
        SUD(0,-1),
        OVEST(-1,0);

        private final int x, y;

        POSIZIONE(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {return x;}
        public int y() {return y;}
    }

    protected PuzzleVax.Puzzle_T tipo;
    protected String tutorial;
    protected Table table;
    protected Stack<Integer> history;
    protected int levelSize;
    protected Queue<Integer> posizioni;
    protected Queue<Image> blocchi;
    protected Queue<String> tiles;
    protected HashMap<String, Integer> map;
    protected VirtualTile[][] board;

    protected Skin skin;

    public abstract void createMap();
    public abstract String createTutorial();
    public abstract void initialize(Queue<String> tiles);
    public boolean singleTouch;

    public Table getTable() {return table;}

    public void loadLevel(int lv) {FileHandle file = Gdx.files.internal(tipo.name() + ".json");
        Json json = new Json();
        json.setSerializer(IntMap.class, new IntMapSerializer());
        IntMap<String> dataMap = json.fromJson(IntMap.class, file);
        String livello = dataMap.get(lv);
        String[] tokens = livello.split("-");
        levelSize = Integer.parseInt(tokens[0]);
        blocchi = new LinkedList<Image>();
        tiles = new LinkedList<String>(Arrays.asList(tokens[1].split("(?!^)")));
        posizioni = new LinkedList<Integer>();
        for (String coord : tokens[2].split("/"))
            posizioni.add(Integer.parseInt(coord));

        initialize(tiles);
    }

    public void createBoard() {
        table = new Table();
        board = new VirtualTile[levelSize][levelSize];
        //int boardSize = calcSize(levelSize, AbstractScreen.P_SIZE);
        //table.setBounds((AbstractScreen.V_WIDTH-boardSize)/2,(AbstractScreen.V_HEIGHT-boardSize)/2,boardSize,boardSize);
        //table.setSize(boardSize,boardSize);
        table.setTouchable(Touchable.enabled);

        for (int i=0; i<levelSize; i++) {
            for (int j=0; j<levelSize; j++) {
                if (i*levelSize+j == posizioni.peek()) {
                    table.add(blocchi.peek());
                    board[i][j] = new VirtualTile(tipo, map.get(tiles.peek()));
                    tiles.remove();
                    blocchi.remove();
                    posizioni.remove();
                }
                else {
                    table.add();
                    board[i][j] = new VirtualTile(tipo, 0);
                }
            }
            table.row();
        }
    }

    private int calcSize(int numBlocks, int baseSize) {
        for (int i=0; i<numBlocks; i++) {
            if ((baseSize+i) % numBlocks == 0)
                return baseSize+i; }
        // Mai raggiunto
        return baseSize;
    }

    protected int getRowIndex (Actor actor, int numBlocks) {
        float centerY = actor.getY()+actor.getHeight()/2;
        float size = actor.getHeight();

        for (int j=0; j<numBlocks; j++)
            if (    centerY > size*j &&
                    centerY < size*(j+1))
                return (numBlocks - j -1); // L'indexing di una Table è diverso da quello di una matrice

        return -1;
    }

    protected int getColumnIndex (Actor actor, int numBlocks) {
        float centerX = actor.getX()+actor.getWidth()/2;
        float size = actor.getWidth();

        for (int i=0; i<numBlocks; i++)
            if (    centerX > size*i &&
                    centerX < size*(i+1))
                return i;

        return -1;
    }
}