package Events;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Event {
    public abstract void update(int tileX, int tileY);
    public abstract void render(SpriteBatch batch);

    protected boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }

}