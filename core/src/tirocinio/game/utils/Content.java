package tirocinio.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class Content {

    private HashMap<String, TextureAtlas> map;

    public Content() {
        map = new HashMap<String, TextureAtlas>();
    }

    public void loadAtlas(String path, String key) {
        map.put(key, new TextureAtlas(Gdx.files.internal(path)));
    }

    public TextureAtlas getAtlas(String key) {
        return map.get(key);
    }
}
