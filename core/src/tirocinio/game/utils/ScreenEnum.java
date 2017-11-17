package tirocinio.game.utils;

import com.badlogic.gdx.Game;

import tirocinio.game.PuzzleVax;
import tirocinio.game.layoutManager.*;

public enum ScreenEnum {

    MAIN_MENU {
        public AbstractScreen getScreen(Game app, Object... params) {
            return new MainMenuScreen((PuzzleVax)app);
        }
    },

    LEVELS {
        public AbstractScreen getScreen(Game app, Object... params) {
            return new LevelScreen((PuzzleVax)app,(PuzzleVax.Puzzle_T) params[0]);
        }
    },

    GAME {
        public AbstractScreen getScreen(Game app, Object... params) {
            return new GameScreen((PuzzleVax)app,(PuzzleVax.Puzzle_T) params[0], (Integer)params[1]);
        }
    },


    SETTINGS {
        public AbstractScreen getScreen(Game app, Object... params) {
            return new SettingsScreen((PuzzleVax)app);
        }
    },

    PROGRESS {
        public AbstractScreen getScreen(Game app, Object... params) {
            return new ProgressScreen((PuzzleVax)app);
        }
    };
    /**
    LOADING {
    public Screen getScreen(Object... params) {
    return new LoadingScreen((PuzzleVax) params[0]);
        }
    },

    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen((Integer) params[0]);
        }
    }; **/

    public abstract AbstractScreen getScreen(Game app, Object... params);
}