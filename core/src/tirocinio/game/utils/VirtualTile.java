package tirocinio.game.utils;

import tirocinio.game.PuzzleVax;

public class VirtualTile {

    private Connettore N,E,S,W;
    private int id;
    private String tipo;

    public VirtualTile(PuzzleVax.Puzzle_T puz, int id) {
        this.id = id;
        N = new Connettore();
        E = new Connettore();
        S = new Connettore();
        W = new Connettore();
        initialize(puz);
    }

    public VirtualTile(PuzzleVax.Puzzle_T puz, String tipo) {
        this.tipo = tipo;
        N = new Connettore();
        E = new Connettore();
        S = new Connettore();
        W = new Connettore();
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

    public Connettore north() {return N;}
    public Connettore east() {return E;}
    public Connettore south() {return S;}
    public Connettore west() {return W;}

    public int getId() {return id;}
    public String getTipo() {return tipo;}
}
