package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import InputManagers.Clickable;
import MainChar.MainCharWalking;

public class HUD  {
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture Home;
    private Texture HP;

    OrthographicCamera camera;

    private MainCharWalking mainCharWalking;


    public HUD() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        Home = new Texture("Home.png");
        HP = new Texture("HP.png");

    }

    public void render() {
        batch.begin();
        batch.draw(Home, 80, Gdx.graphics.getHeight() - Home.getHeight() - 965);
        batch.draw(HP, 180, Gdx.graphics.getHeight() - HP.getHeight() - HP.getHeight() - 900);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}