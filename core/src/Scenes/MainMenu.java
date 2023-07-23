package Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import InputManagers.Clickable;
import InputManagers.InputHandler;
import Screens.PlayScreen;
import Screens.TwoSuns;
import de.eskalon.commons.screen.ManagedScreen;

public class MainMenu extends ManagedScreen implements Clickable {
    private TwoSuns twoSuns;
    private SpriteBatch batch;
    private Viewport viewport;
    private Texture mainMenuBackground;
    private BitmapFont font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font2;
    private FreeTypeFontGenerator fontGenerator2;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2;

    private InputHandler inputHandler;
    private List<Clickable> clickables;

    public MainMenu(TwoSuns twoSuns) {
        this.twoSuns = twoSuns;
        batch = new SpriteBatch();
        mainMenuBackground = new Texture("back3.png");
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Font1.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 232;
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        font = fontGenerator.generateFont(fontParameter);

        fontGenerator2 = new FreeTypeFontGenerator(Gdx.files.internal("Font1.ttf")); //fonts/DOWNCOME.TTF
        fontParameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter2.size = 116;
        fontParameter2.minFilter = Texture.TextureFilter.Linear;
        fontParameter2.magFilter = Texture.TextureFilter.Linear;
        font2 = fontGenerator2.generateFont(fontParameter2);
    }

    @Override
    protected void create() {

    }

    @Override
    public void show() {
        super.show();
        clickables = new ArrayList<>();
        clickables.add(this);

        inputHandler = new InputHandler(clickables);
        twoSuns.addInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(mainMenuBackground,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        String text = "Two SUNS \nin the Sunset";
        float x = 100;
        float y = (float) (Gdx.graphics.getHeight() * 0.5); //550;

        font.draw(batch, text, x, y);

        x = (float) ((float) Gdx.graphics.getWidth() * 0.65);
        font2.draw(batch, "- New Game",x , y);
        font2.draw(batch, "- Load Game",x , y - 150);
        font2.draw(batch, "- Settings",x , y - 300);
        batch.end();
    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        fontGenerator.dispose();
        fontGenerator2.dispose();
        font.dispose();
        font2.dispose();
        batch.dispose();
        mainMenuBackground.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("HEEEEJ");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}