package Events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import InputManagers.Clickable;
import MainChar.MainCharWalking;
public class CarEvent extends Event implements Clickable {
    private TextureRegion uiTexture;
    private OrthographicCamera camera;
    private int triggerTileX;
    private int triggerTileY;
    private BitmapFont font;
    private String text;
    private String playerText;

    private float letterSpawnTime = 0.05f;
    private float timer = 0;
    private String drawText = "";
    private int stringIndex = 0;

    private Boolean execute;


    private MainCharWalking mainChar;

    public CarEvent(OrthographicCamera camera, int triggerTileX, int triggerTileY, MainCharWalking mainChar) {
        this.camera = camera;
        this.mainChar = mainChar;
        uiTexture = new TextureRegion(new Texture("TextBox.png"));
        execute = false;
        this.triggerTileX = triggerTileX;
        this.triggerTileY = triggerTileY;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);
        generator.dispose();

        text = "This is my junk." + "\n Leave me alone";

        playerText = "- Bye...";
    }

    public void update(int tileX, int tileY) {
        if (tileX == triggerTileX && tileY == triggerTileY) {
            execute();
        }

        if (execute) {
            float fixedDelta = Gdx.graphics.getDeltaTime();
            timer += fixedDelta;
            if (timer >= letterSpawnTime && stringIndex < text.length()) {
                drawText = text.substring(0, stringIndex + 1);
                stringIndex++;
                timer -= letterSpawnTime;
            }
        }
    }

    public void execute() {
        mainChar.setMoving(false);
        execute = true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (execute) {
            Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
            float touchX = worldCoordinates.x;
            float touchY = worldCoordinates.y;

            float centerX = camera.position.x - uiTexture.getRegionWidth() / 2f;
            float centerY = camera.position.y - uiTexture.getRegionHeight() / 2f;

            float ptextX = centerX + uiTexture.getRegionWidth() * 0.1f;
            float ptextY = centerY + uiTexture.getRegionHeight() * 0.4f;

            GlyphLayout layout = new GlyphLayout(font, playerText);
            float ptextWidth = layout.width;
            float ptextHeight = layout.height;

            if (touchX >= ptextX && touchX <= ptextX + ptextWidth &&
                    touchY >= ptextY && touchY <= ptextY + ptextHeight) {
                isActive = false;
                execute = false;
                mainChar.setMoving(true);
                return true;
            }
        }

        return false;
    }


    public void render(SpriteBatch batch) {
        if (execute && isActive) {
            float centerX = camera.position.x - uiTexture.getRegionWidth() / 2f;
            float centerY = camera.position.y - uiTexture.getRegionHeight() / 2f;
            batch.draw(uiTexture, centerX, centerY);
            font.setColor(Color.WHITE);

            float textX = centerX + uiTexture.getRegionWidth() * 0.1f;
            float textY = centerY + uiTexture.getRegionHeight() * 0.8f;

            float ptextX = centerX + uiTexture.getRegionWidth() * 0.1f;
            float ptextY = centerY + uiTexture.getRegionHeight() * 0.4f;

            if (timer >= letterSpawnTime && stringIndex < text.length()) {
                drawText = text.substring(0, stringIndex + 1);
                stringIndex++;
                timer = 0;
            }

            font.draw(batch, drawText, textX, textY);
            font.draw(batch, playerText, ptextX, ptextY);
        }

    }
    public boolean isActive() {
        return execute;
    }
}

