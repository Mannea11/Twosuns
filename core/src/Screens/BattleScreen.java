package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Scenes.BattleRender;


public class BattleScreen implements Screen {
    private BattleRender battleRender;

    private TwoSuns twoSuns;

    private SpriteBatch batch;

    public BattleScreen(TwoSuns twoSuns) {
        this.twoSuns = twoSuns;
        battleRender = new BattleRender();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        battleRender.render(batch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
