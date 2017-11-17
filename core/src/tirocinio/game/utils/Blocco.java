package tirocinio.game.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import tirocinio.game.PuzzleVax;

public class Blocco extends com.badlogic.gdx.scenes.scene2d.ui.Image {

    private String tipo;

    public Blocco(String tipo, PuzzleVax.Puzzle_T puz, Skin skin) {
        super(skin, puz.name() + "_" + tipo);
        this.tipo = tipo;
    }

    public String tipo() {return tipo;}
}
