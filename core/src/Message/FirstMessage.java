package Message;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


import InputManagers.Clickable;
import MainChar.MainCharWalking;

public class FirstMessage implements Clickable {
    private TextureRegion uiTexture;
    private OrthographicCamera camera;
    private BitmapFont font;
    private String text;
    private float letterSpawnTime = 0.10f;
    private float timer = 0;
    private String drawText = "";
    private int stringIndex = 0;
    private boolean isActive = true;

    private MainCharWalking mainChar;

    public FirstMessage(OrthographicCamera camera, MainCharWalking mainChar) {
        this.camera = camera;
        uiTexture = new TextureRegion(new Texture("TextBoxS.png"));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        font = generator.generateFont(parameter);
        generator.dispose();
        this.mainChar = mainChar;

        text = "Where..am..i?\n.... \n.. \n...\nWho..am..i?";
    }

    public void update() {
        float fixedDelta = Gdx.graphics.getDeltaTime();
        timer += fixedDelta;
        if (timer >= letterSpawnTime && stringIndex < text.length()) {
            drawText = text.substring(0, stringIndex + 1);
            stringIndex++;
            timer -= letterSpawnTime;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isActive = false;
        mainChar.setMoving(true);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public void render(SpriteBatch batch) {
        if (isActive) {
            mainChar.setMoving(false);
            float centerX = camera.position.x - uiTexture.getRegionWidth() / 2f;
            float centerY = camera.position.y - uiTexture.getRegionHeight() / 2f;
            batch.draw(uiTexture, centerX, centerY);
            font.setColor(Color.WHITE);

            float textX = centerX + uiTexture.getRegionWidth() * 0.1f;
            float textY = centerY + uiTexture.getRegionHeight() * 0.8f;

            if (timer >= letterSpawnTime && stringIndex < text.length()) {
                drawText = text.substring(0, stringIndex + 1);
                stringIndex++;
                timer = 0;
            }

            font.draw(batch, drawText, textX, textY);
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
