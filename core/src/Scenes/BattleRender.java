package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import Enemies.Enemies;
import InputManagers.Clickable;
import MainChar.MainCharAttributes;
import MainChar.MainCharWalking;
import Screens.TwoSuns;

public class BattleRender implements Clickable {

    private Texture dice1;
    private Texture dice2;
    private Texture dice3;

    private BitmapFont font;

    private Texture heart;

    private Texture attack1;

    private Texture defence1;

    private Texture attack2;

    private Texture defence2;

    private Texture attack3;

    private Texture defence3;

    private MainCharAttributes mainCharAttributes;
    private Enemies enemy;

    private TwoSuns twoSuns;

    private Texture enemyTexture;

    private MainCharWalking mainCharWalking;

    private GameMap gameMap;

    private OrthographicCamera camera;

    private int randomPngsToShow = 5;

    private Random random;


    public BattleRender(TwoSuns twoSuns) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        font = generator.generateFont(parameter);
        generator.dispose();
        mainCharAttributes = new MainCharAttributes();
        this.twoSuns = twoSuns;
        gameMap = new GameMap();
        mainCharWalking = new MainCharWalking(twoSuns,camera,gameMap.getGameMap());
        random = new Random();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setEnemy(Enemies enemy) {
        this.enemy = enemy;

    }


    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        dice1 = getRandomTexture();
        dice2 = new Texture("dice6.png");
        dice3 = new Texture("dice6.png");
        heart = new Texture("HP.png");
        attack1 = new Texture("Attack.png");
        defence1 = new Texture("Block.png");
        attack2 = new Texture("Attack.png");
        defence2 = new Texture("Block.png");
        attack3 = new Texture("Attack.png");
        defence3 = new Texture("Block.png");

        float heartWidth = heart.getWidth();
        float heartHeight = heart.getHeight();
        float heartStartX = 200;
        float heartStartY = 200;

        float diceWidth = dice1.getWidth();
        float diceHeight = dice1.getHeight();
        float diceSpacing = 40f;

        float totalWidth = (3 * diceWidth) + (2 * diceSpacing);
        float dicestartX = centerX - (totalWidth / 2);
        float dicestartY = centerY - (diceHeight / 2);

        float hpTextX = heartStartX + heartWidth + 10;
        float hpTextY = heartStartY + font.getLineHeight();

        float attackWidth = attack1.getWidth();
        float attachHeight = attack1.getHeight();
        float attack1StartX = dicestartX;
        float attack1StartY = dicestartY - 150;

        //   float enemyTextureX = centerX;
        //  float enemyTextureY = centerY;


        //   batch.draw(enemyTexture, enemyTextureX, enemyTextureY);
        batch.draw(heart, heartStartX, heartStartY);
        batch.draw(dice1, dicestartX, dicestartY);
        batch.draw(dice2, dicestartX + diceWidth + diceSpacing, dicestartY);
        batch.draw(dice3, dicestartX + (2 * diceWidth) + (2 * diceSpacing), dicestartY);
        batch.draw(attack1, attack1StartX, attack1StartY);
        batch.draw(attack2, dicestartX + diceWidth + diceSpacing, dicestartY - 150);
        batch.draw(defence1, dicestartX + (2 * diceWidth) + (2 * diceSpacing), dicestartY - 150);
        font.draw(batch, "" + mainCharAttributes.getHp() + "/" + mainCharAttributes.getMaxHp(), hpTextX, hpTextY);

        float enemyTextX = 930;
        float enemyTextY = Gdx.graphics.getHeight() - 60;

        font.draw(batch, "Enemy: " + enemy.getClass().getSimpleName(), enemyTextX, enemyTextY - font.getLineHeight());
        font.draw(batch, "HP: " + enemy.getHealth(), enemyTextX, enemyTextY - 2 * font.getLineHeight());
        font.draw(batch, "Attack: " + enemy.getAttack(), enemyTextX, enemyTextY - 3 * font.getLineHeight());
        font.draw(batch, "Defense: " + enemy.getDefense(), enemyTextX, enemyTextY - 4 * font.getLineHeight());

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      // this.twoSuns.getScreenManager().pushScreen("PlayScreen", "Horizontal");
      //  mainCharWalking.setMoving(true);
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 dragPosition = new Vector3(screenX, screenY, 0);
        camera.unproject(dragPosition);
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        float diceHeight = dice1.getHeight();
        float diceWidth = dice1.getWidth();
        float diceSpacing = 40f;
        float totalWidth = (3 * diceWidth) + (2 * diceSpacing);
        float dicestartX = centerX - (totalWidth / 2);
        float dicestartY = centerY - (diceHeight / 2);

        if (dragPosition.x >= dicestartX && dragPosition.x <= dicestartX + diceWidth &&
                dragPosition.y >= dicestartY && dragPosition.y <= dicestartY + diceHeight) {

            if (randomPngsToShow > 0) {
                int randomNum = random.nextInt(5) + 1;
                switch (randomNum) {
                    case 1:
                        dice1 = new Texture("dice1.png");
                        break;
                    case 2:
                        dice1 = new Texture("dice2.png");
                        break;
                    case 3:
                        dice1 = new Texture("dice3.png");
                        break;
                    case 4:
                        dice1 = new Texture("dice4.png");
                        break;
                    case 5:
                        dice1 = new Texture("dice5.png");
                        break;
                }
                randomPngsToShow--;
            } else {
                dice1 = getRandomTexture();
                randomPngsToShow = 5;
            }

            System.out.println("DRAG");
            return true;
        }
        return false;
    }
}