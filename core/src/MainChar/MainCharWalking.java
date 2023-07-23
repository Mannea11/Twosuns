package MainChar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import Enemies.Enemies;
import Enemies.Rat;
import Enemies.Wolf;

import InputManagers.Clickable;
import Screens.BattleScreen;
import Screens.TwoSuns;

public class MainCharWalking implements Clickable {
    private static final float MOVEMENT_SPEED = 75.0f;

    private Sprite sprite;
    private Vector2 position;
    private Vector2 targetPosition;
    private OrthographicCamera camera;
    private TiledMap gameMap;
    private float zoomScale = 2.1f;
    private boolean isMoving = true;
    private float stepsTaken;
    private Game game;
    private TwoSuns twoSuns;


    public MainCharWalking(TwoSuns twoSuns, OrthographicCamera camera, TiledMap tiledMap) {
        this.twoSuns = twoSuns;
        this.gameMap = tiledMap;
        this.camera = camera;
        Texture texture = new Texture("Boy.png");
        sprite = new Sprite(texture);
        position = new Vector2(2218, 4369);
        targetPosition = new Vector2(position);
        stepsTaken = 0.0f;

    }

    public void update(float deltaTime) {
        updateCloudsVisibility();

        if (isMoving) {
            EnemyTier1();
            System.out.println(position.x + " " + position.y);
            float distance = position.dst(targetPosition);
            if (distance > 1.0f) {
                stepsTaken += 0.01f;
                float movementAmount = MOVEMENT_SPEED * deltaTime;
                float movementDelta = Math.min(movementAmount, distance);
                position = position.lerp(targetPosition, movementDelta / distance);
                camera.position.set(position, 0);
                camera.update();
                getCurrentTile();
                isPositionOnObjectTile(position);

            }

        }

    }


    private void getCurrentTile() {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) gameMap.getLayers().get(0);

        if (tileLayer != null) {
            int tileX = (int) (position.x / tileLayer.getTileWidth());
            int tileY = (int) (position.y / tileLayer.getTileHeight());

            //System.out.println("Current Tile: X = " + tileX + ", Y = " + tileY);
            //  System.out.println("Steps Taken: " + stepsTaken  );
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void printMapLayers() {
        MapLayers mapLayers = gameMap.getLayers();
        for (MapLayer mapLayer : mapLayers) {
            System.out.println("Layer Name: " + mapLayer.getName());
        }
    }

    public void dispose() {
        sprite.getTexture().dispose();

    }


    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (isMoving) {
            Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            float clickX = worldCoordinates.x;
            float clickY = worldCoordinates.y;

            if (isPositionOnObjectTile(new Vector2(clickX, clickY))) {
                setMoving(false);
                return true;
            } else {
                targetPosition.x = clickX;
                targetPosition.y = clickY;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    private boolean isPositionOnObjectTile(Vector2 position) {
        MapLayers mapLayers = gameMap.getLayers();
        MapLayer objectLayer = mapLayers.get("Object");

        if (objectLayer != null) {
            MapObjects objects = objectLayer.getObjects();

            for (MapObject object : objects) {
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    Rectangle rect = rectObject.getRectangle();

                    float zoomedX = rect.x * zoomScale;
                    float zoomedY = rect.y * zoomScale;
                    float zoomedWidth = rect.width * zoomScale;
                    float zoomedHeight = rect.height * zoomScale;

                    Rectangle zoomedRect = new Rectangle(zoomedX, zoomedY, zoomedWidth, zoomedHeight);

                    if (zoomedRect.contains(position)) {
                        System.out.println("Walking on ObjectLayer");
                        return true;
                    }
                }
            }
        } else {
            System.out.println("ObjectLayer is null");
        }

        return false;
    }

    public float getStepsTaken() {
        return stepsTaken;
    }

    private void updateCloudsVisibility() {
        TiledMapTileLayer cloudTileLayer = (TiledMapTileLayer) gameMap.getLayers().get("Clouds");

        if (cloudTileLayer != null) {
            int tileX = (int) (position.x / (cloudTileLayer.getTileWidth() * zoomScale));
            int tileY = (int) (position.y / (cloudTileLayer.getTileHeight() * zoomScale));

            int tileSizeX = 2;
            int tileSizeY = 2;

            for (int x = tileX - tileSizeX; x <= tileX + tileSizeX; x++) {
                for (int y = tileY - tileSizeY; y <= tileY + tileSizeY; y++) {
                    TiledMapTileLayer.Cell cell = cloudTileLayer.getCell(x, y);
                    if (cell != null) {
                        cloudTileLayer.setCell(x, y, null);
                    }

                }
            }
        }
    }

    private boolean isPositionOnTier1(Vector2 position) {
        MapLayers mapLayers = gameMap.getLayers();
        MapLayer objectLayer = mapLayers.get("Tier1");

        if (objectLayer != null) {
            MapObjects objects = objectLayer.getObjects();

            for (MapObject object : objects) {
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    Rectangle rect = rectObject.getRectangle();

                    float zoomedX = rect.x * zoomScale;
                    float zoomedY = rect.y * zoomScale;
                    float zoomedWidth = rect.width * zoomScale;
                    float zoomedHeight = rect.height * zoomScale;

                    Rectangle zoomedRect = new Rectangle(zoomedX, zoomedY, zoomedWidth, zoomedHeight);

                    if (zoomedRect.contains(position)) {
                        System.out.println("Walking on Tier1");
                        return true;
                    }
                }
            }
        } else {
            System.out.println("ObjectLayer is null");
        }

        return false;
    }

    private boolean EnemyTier1() {
        if (isPositionOnTier1(position) && Math.random() < 0.005) {
            System.out.println("Enemy encounter!");
            isMoving = false;
            this.twoSuns.getScreenManager().pushScreen("BattleScreen", "Horizontal");
        }
        return false;
    }
}