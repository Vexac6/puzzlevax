package tirocinio.game.utils;

import tirocinio.game.PuzzleVax;

public class VirtualTile {

    private Polo N,E,S,W;
    private int id;
    private String tipo;

    public VirtualTile(PuzzleVax.Puzzle_T puz, int id) {
        this.id = id;
        N = new Polo();
        E = new Polo();
        S = new Polo();
        W = new Polo();
        initialize(puz);
    }

    public VirtualTile(PuzzleVax.Puzzle_T puz, String tipo) {
        this.tipo = tipo;
        N = new Polo();
        E = new Polo();
        S = new Polo();
        W = new Polo();
        initialize(puz, tipo);
    }

    private void initialize(PuzzleVax.Puzzle_T puz) {
        if (id == 0) return; // Spazio vuoto
        switch (puz) {
            case ROAD: {
                if (id%2 == 0) N.set();
                if (id%3 == 0) E.set();
                if (id%5 == 0) S.set();
                if (id%7 == 0) W.set();
            }
        }

    }

    private void initialize(PuzzleVax.Puzzle_T puz, String tipo) {    }

    public int polesConnected() {
        int poles = 0;
        if (north().isConnected()) poles++;
        if (east().isConnected()) poles++;
        if (south().isConnected()) poles++;
        if (west().isConnected()) poles++;
        return poles;
    }

    public Polo north() {return N;}
    public Polo east() {return E;}
    public Polo south() {return S;}
    public Polo west() {return W;}
    public int getId() {return id;}
    public String getTipo() {return tipo;}
}
