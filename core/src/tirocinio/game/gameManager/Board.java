package tirocinio.game.gameManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import tirocinio.game.layoutManager.AbstractScreen;
import tirocinio.game.utils.Blocco;

public class Board extends Table {

    public String[][] virtual;

    public Board (int numBlocks) {
        super();
        int vWidth = AbstractScreen.V_WIDTH;
        int vHeight = AbstractScreen.V_HEIGHT;
        int pSize = AbstractScreen.P_SIZE;
        virtual = new String[numBlocks][numBlocks];
        int boardSize = calcSize(numBlocks, pSize);
        setBounds((vWidth-boardSize)/2,(vHeight-boardSize)/2,boardSize,boardSize);
        this.setTouchable(Touchable.enabled);
    }

    private int calcSize(int numBlocks, int baseSize) {
        for (int i=0; i<numBlocks; i++) {
            if ((baseSize+i) % numBlocks == 0)
                return baseSize+i; }
        // Mai raggiunto
        return baseSize;
    }

    public Cell add(int row, int col){
        virtual[row][col] = "0";
        return super.add();
    }

    public Cell add(Blocco blocco, int row, int col){
        //virtual[row][col] = blocco.getState();
        return super.add(blocco);
    }

    public int getRowIndex (Blocco blocco, int numBlocks) {
        float centerY = blocco.getY()+blocco.getHeight()/2;
        float size = blocco.getHeight();

        for (int j=0; j<numBlocks; j++)
                if (    centerY > size*j &&
                        centerY < size*(j+1))
                    return (numBlocks - j -1); // L'indexing di una Table è diverso da quello di una matrice

        return -1;
    }

    public int getColumnIndex (Blocco blocco, int numBlocks) {
        float centerX = blocco.getX()+blocco.getWidth()/2;
        float size = blocco.getWidth();

        for (int i=0; i<numBlocks; i++)
            if (    centerX > size*i &&
                    centerX < size*(i+1))
                return i;

        return -1;
    }

    public boolean contains(float x, float y) {
        if (x >= getX() &&
                x <= getX()+getWidth() &&
                y >= getY() &&
                y <= getY()+getHeight())
            return true;
        else return false;
    }

    public boolean contains(Vector2 vector) {
        if (vector.x >= getX() &&
                vector.x <= getX()+getWidth() &&
                vector.y >= getY() &&
                vector.y <= getY()+getHeight())
            return true;
        else return false;
    }

    public Cell getCell(int size, float x, float y) {
        if (contains(x,y)) {
            float bx = getX();
            float by = getY();
            float s = getWidth();
            float cs = s/size;
            for (int i=0; i<size; i++)
                for (int j=0; j<size; j++) {
                    if (x >= bx+j*cs &&
                            x <= bx+(j+1)*cs &&
                            y >= by+s-(i+1)*cs &&
                            y <= by+s+i*cs)
                        return getCells().get(i*size+j);
                }
        }
        return null;
    }
}
