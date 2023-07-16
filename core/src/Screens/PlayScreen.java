package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

import Events.CarEvent;
import Events.Event;
import Events.HouseEvent;
import InputManagers.Clickable;
import InputManagers.InputHandler;
import Message.FirstMessage;
import MainChar.MainCharWalking;
import Scenes.GameMap;
import Scenes.HUD;


public class PlayScreen implements Screen {
    private Game game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private MainCharWalking mainChar;
    private GameMap gameMap;
    private HouseEvent houseEvent;

    private CarEvent carEvent;
    private List<Event> events;
    private int tileWidth;
    private int tileHeight;
    private TiledMap tiledMap;

    private List<Clickable> clickables;
    private InputHandler inputHandler;

    private FirstMessage firstMessage;
    private HUD hud;

    private TwoSuns twoSuns;


    public PlayScreen(TwoSuns twoSuns) {
        this.twoSuns = twoSuns;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        gameMap = new GameMap();
        mainChar = new MainCharWalking(twoSuns,camera, gameMap.getGameMap());
        mainChar.printMapLayers();
        tiledMap = new TmxMapLoader().load("ny.tmx");
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        tileWidth = (int) tileLayer.getTileWidth();
        tileHeight = (int) tileLayer.getTileHeight();
        houseEvent = new HouseEvent(camera, 11, 56,mainChar);
        carEvent = new CarEvent(camera,39,58,mainChar);
        firstMessage = new FirstMessage(camera,mainChar);
        hud = new HUD();

        events = new ArrayList<>();
        events.add(houseEvent);
        events.add(carEvent);


        clickables = new ArrayList<>();
        clickables.add(mainChar);
        clickables.add(houseEvent);
        clickables.add(carEvent);
        clickables.add(firstMessage);


        inputHandler = new InputHandler(clickables,mainChar);
        Gdx.input.setInputProcessor(inputHandler);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        int tileX = (int) (mainChar.getPosition().x / tileWidth);
        int tileY = (int) (mainChar.getPosition().y / tileHeight);

        batch.begin();
        gameMap.render(camera);

        mainChar.update(delta);
        mainChar.render(batch);

        firstMessage.render(batch);
        firstMessage.update();

        for (Event event : events) {
                event.render(batch);
            }
        for (Event event : events) {
            event.update(tileX, tileY);
        }
        batch.end();
        hud.render();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(mainChar.getPosition().x, mainChar.getPosition().y, 0);

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
        batch.dispose();
        hud.dispose();
        gameMap.dispose();
    }


}
