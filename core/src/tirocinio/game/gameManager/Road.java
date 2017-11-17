package tirocinio.game.gameManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.HashMap;
import java.util.Queue;

import tirocinio.game.PuzzleVax;
import tirocinio.game.utils.VirtualTile;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Road extends Puzzle {

    public Road(int lv) {
        tipo = PuzzleVax.Puzzle_T.ROAD;
        tutorial = createTutorial();
        createMap();
        loadLevel(lv);
        createBoard();
        connectAll();

        for(int i=0; i<levelSize; i++) {
            for (int j = 0; j < levelSize; j++)
                System.out.print(board[i][j].getId()+" ");
            System.out.println();
        }

        for(int i=0; i<levelSize; i++) {
            for (int j = 0; j < levelSize; j++) {
                int connessioni = 0;
                if (board[i][j].north().isConnected()) i++;
                if (board[i][j].east().isConnected()) i++;
                if (board[i][j].south().isConnected()) i++;
                if (board[i][j].west().isConnected()) i++;
                System.out.print(connessioni);
            }
            System.out.println();
        }
    }

    @Override
    public void createMap() {
        map = new HashMap<String, Integer>();
        map.put("A", 35);
        map.put("B", 15);
        map.put("C", 6);
        map.put("D", 5);
        map.put("E", 14);
        map.put("H", 21);
        map.put("I", 10);
        map.put("J", 21);
        map.put("L", 7);
        map.put("M", 35);
        map.put("N", 15);
        map.put("O", 6);
        map.put("P", 14);
        map.put("R", 3);
        map.put("U", 2);
        map.put("V", 10);
        map.put("X", 1);
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

                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        final POSIZIONE movimento = checkMovement(x,y);
                        if (movimento != null && haSpazio(img, movimento))
                            img.addAction(sequence(
                                    Actions.moveBy(img.getWidth()*movimento.x(),
                                                   img.getWidth()*movimento.y(), .2f),
                                    run (new Runnable(){
                                        @Override
                                        public void run() {
                                            if (checkCompletion(
                                                    getRowIndex(img,levelSize),
                                                    getColumnIndex(img,levelSize)))
                                                Gdx.app.log("PUZZLE", " COMPLETATO!");
                                        }
                                    })));
                    }
                });
            }
            blocchi.add(img);
        }
    }

    @Override
    public String createTutorial() {
        return tutorial;
    }

    private void connectAll() {
        for (int i=0; i<levelSize; i++)
            for (int j=0; j<levelSize; j++)
                setConnections(i,j);

    }

    private void setConnections(int row, int col) {
        if (board[row][col].getId() == 0) return;
        if (row != 0 && board[row-1][col].south().isPresent()) board[row][col].north().connect();
        if (col != 0 && board[row][col-1].east().isPresent()) board[row][col].west().connect();
        if (row != levelSize-1 && board[row+1][col].north().isPresent()) board[row][col].south().connect();
        if (col != levelSize-1 && board[row][col+1].west().isPresent()) board[row][col].east().connect();
    }

    private void setDisonnections(int row, int col) {
        if (board[row][col].getId() != 0) return;
        if (row != 0) board[row-1][col].north().disconnect();
        if (col != 0) board[row][col-1].west().disconnect();
        if (row != levelSize-1) board[row+1][col].south().disconnect();
        if (col != levelSize-1) board[row][col+1].east().disconnect();
    }

    private boolean checkCompletion(int row, int col) {
        int roadBuilt = 0;
        if (board[row][col].north().isConnected() && checkRoad(row-1,col,POSIZIONE.SUD))
            roadBuilt++;
        if (board[row][col].east().isConnected() && checkRoad(row,col+1,POSIZIONE.OVEST))
            roadBuilt++;
        if (board[row][col].south().isConnected() && checkRoad(row+1,col,POSIZIONE.NORD))
            roadBuilt++;
        if (board[row][col].west().isConnected() && checkRoad(row,col-1,POSIZIONE.EST))
            roadBuilt++;

        return roadBuilt == 2;
    }

    private boolean checkRoad(int row, int col, POSIZIONE prev) {
        if (board[row][col].north().isConnected() && prev != POSIZIONE.NORD)
            if (board[row-1][col].getId() == 5) return true;
            else return checkRoad(row-1,col,POSIZIONE.SUD);
        if (board[row][col].east().isConnected() && prev != POSIZIONE.EST)
            if (board[row][col+1].getId() == 7) return true;
            else return checkRoad(row,col+1,POSIZIONE.OVEST);
        if (board[row][col].south().isConnected() && prev != POSIZIONE.SUD)
            if (board[row+1][col].getId() == 2) return true;
            else return checkRoad(row-1,col,POSIZIONE.SUD);
        if (board[row][col].west().isConnected() && prev != POSIZIONE.OVEST)
            if (board[row][col-1].getId() == 3) return true;
            else return checkRoad(row,col-1,POSIZIONE.EST);
        return false;
    }

    private POSIZIONE checkMovement(float x, float y) {
        float modX = Math.abs(x);
        float modY = Math.abs(y);
        if (y > 0 && modY > modX)
            return POSIZIONE.NORD;
        if (y < 0 && modY > modX)
            return POSIZIONE.SUD;
        if (x > 0 && modX > modY)
            return POSIZIONE.EST;
        if (x < 0 && modX > modY)
            return POSIZIONE.OVEST;
        return null;
    }

    private boolean haSpazio (Image blocco, POSIZIONE pos){
        int row = getRowIndex(blocco, levelSize);
        int col = getColumnIndex(blocco, levelSize);
        VirtualTile dummy = board[row][col];
        switch(pos) {
            case NORD:
                if (row != 0 && board[row-1][col].getId() == 0) {
                    board[row][col] = board[row-1][col];
                    setDisonnections(row, col);
                    board[row-1][col] = dummy;
                    setConnections(row-1, col);
                    return true;
                } else return false;
            case EST:
                if (col != levelSize-1 && board[row][col+1].getId() == 0){
                    board[row][col] = board[row][col+1];
                    setDisonnections(row, col);
                    board[row][col+1] = dummy;
                    setConnections(row, col+1);
                    return true;
                } else return false;
            case SUD:
                if (row != levelSize-1 && board[row+1][col].getId() == 0){
                    board[row][col] = board[row+1][col];
                    setDisonnections(row, col);
                    board[row+1][col] = dummy;
                    setConnections(row+1, col);
                    return true;
                } else return false;
            case OVEST:
                if (col != 0 && board[row][col-1].getId() == 0){
                    board[row][col] = board[row][col-1];
                    setDisonnections(row, col);
                    board[row][col-1] = dummy;
                    setConnections(row, col-1);
                    return true;
                } else return false;
            default: return false;
        }
    }
}