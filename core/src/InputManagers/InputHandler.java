package InputManagers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.List;
import MainChar.MainCharWalking;
import Scenes.BattleRender;

public class InputHandler implements InputProcessor {

    private OrthographicCamera camera;
    private List<Clickable> clickables;


    public InputHandler(List<Clickable> clickables) {
        this.clickables = clickables;
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (Clickable clickable : clickables) {
            if (clickable.touchDown(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Clickable clickable : clickables) {
            if (clickable.touchDragged(screenX, screenY, pointer)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    }


