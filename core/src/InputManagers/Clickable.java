package InputManagers;

public interface Clickable {
    boolean touchDown(int screenX, int screenY, int pointer, int button);

    boolean touchDragged(int screenX, int screenY, int pointer);
}

