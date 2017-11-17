package tirocinio.game.layoutManager;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class HUD extends Table {

    HUD (Skin skin, Button b1, Button b2) {
        super(skin);
        setBackground(skin.getDrawable("HUD_BG"));
        add(b1).padLeft(AbstractScreen.V_WIDTH/20);
        add("").expandX();
        add(b2).padRight(AbstractScreen.V_WIDTH/20);
    }

    HUD (Skin skin, Button b1, Button b2, Button b3) {
        super();
        setBackground(skin.getDrawable("HUD_BG"));
        add(b1).padLeft(AbstractScreen.V_WIDTH/20);
        add(b2).expandX();
        add(b3).padRight(AbstractScreen.V_WIDTH/20);
    }
}
