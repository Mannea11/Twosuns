package Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import MainChar.MainCharWalking;

public class GameMap {
    private TiledMap gameMap;
    private TiledMapRenderer tiledMapRenderer;
    private float zoomScale = 2.1f;

    private MainCharWalking mainChar;

    private OrthographicCamera camera;
    private MapLayer cloudLayer;

    public GameMap() {
        gameMap = new TmxMapLoader().load("ny.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(gameMap, zoomScale);
        cloudLayer = gameMap.getLayers().get("Clouds");

    }


    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
    public TiledMap getGameMap() {
        return gameMap;
    }


    public void printMapLayers() {
        MapLayers mapLayers = gameMap.getLayers();
        for (MapLayer mapLayer : mapLayers) {
            System.out.println("Layer Name: " + mapLayer.getName());
        }
    }
    public void dispose() {
        gameMap.dispose();
    }
}
