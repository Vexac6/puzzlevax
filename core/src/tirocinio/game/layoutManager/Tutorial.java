package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import tirocinio.game.PuzzleVax;

public class Tutorial extends Window {

    private TextButton next;
    private final int puzzleID;

    public Tutorial (Skin skin, int id) {
        super(PuzzleVax.Puzzle_T.values()[id].name(), skin);
        next = new TextButton("Avanti", skin);
        puzzleID = id;
        initialize();
    }

    public void initialize() {

    }
}
