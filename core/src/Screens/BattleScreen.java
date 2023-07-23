package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Enemies.Enemies;
import Enemies.Rat;
import Enemies.Wolf;
import InputManagers.Clickable;
import InputManagers.InputHandler;
import Scenes.BattleRender;
import de.eskalon.commons.screen.ManagedScreen;


public class BattleScreen extends ManagedScreen {

    private TwoSuns twoSuns;
    private Viewport viewport;

    private SpriteBatch batch;

    private InputHandler inputHandler;
    private BattleRender battleRender;

    private List<Clickable> clickables;

    private Enemies enemies;
    public BattleScreen(TwoSuns twoSuns) {

    }

    private Enemies getRandomEnemy() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);

        if (randomNumber == 0) {
            return new Rat();
        } else {
            return new Wolf();
        }
    }

    @Override
    protected void create() {
        this.twoSuns = (TwoSuns) Gdx.app.getApplicationListener();
        this.batch = new SpriteBatch();
        battleRender = new BattleRender(twoSuns);
       this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }
    @Override
    public void show() {
        super.show();
        clickables = new ArrayList<>();
        clickables.add(battleRender);
        inputHandler = new InputHandler(clickables);
        twoSuns.addInputProcessor(inputHandler);
        battleRender.setEnemy(getRandomEnemy());

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        battleRender.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }

}
