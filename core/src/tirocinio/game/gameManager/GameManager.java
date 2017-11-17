package tirocinio.game.gameManager;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Stack;

public class GameManager {

    private Stack<Stato> stati;

    public GameManager() {
        stati = new Stack<Stato>();
    }

    public void push(Stato s) {
        stati.push(s);
    }

    public void pop() {
        stati.pop();
    }

    public void set(Stato s) {
        stati.pop();
        stati.push(s);
    }
    public void update(float dt) {
        stati.peek().update(dt);
    }

    public void render() {
        stati.peek().render();
    }

}
