package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import MainChar.MainCharAttributes;

public class BattleRender {

    private Texture dice1;
    private Texture dice2;
    private Texture dice3;

    private Texture font;

    private Texture heart;
    
    private Texture attack;

    private Texture defence;

    private MainCharAttributes mainCharAttributes;

    public BattleRender() {

    }

    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        dice1 = new Texture("dice6.png");
        dice2 = new Texture("dice6.png");
        dice3 = new Texture("dice6.png");
        heart = new Texture("HP.png");
     //   attack = new Texture("");
      //  defence = new Texture("");

        float diceWidth = dice1.getWidth();
        float diceHeight = dice1.getHeight();
        float diceSpacing = 20f;

        float totalWidth = (3 * diceWidth) + (2 * diceSpacing);
        float startX = centerX - (totalWidth / 2);
        float startY = centerY - (diceHeight / 2);

        batch.draw(dice1, startX, startY);
        batch.draw(dice2, startX + diceWidth + diceSpacing, startY);
        batch.draw(dice3, startX + (2 * diceWidth) + (2 * diceSpacing), startY);

        batch.end();
    }

}
