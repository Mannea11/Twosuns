package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import MainChar.MainCharWalking;

public class TwoSuns extends Game {

	private PlayScreen playScreen;
	private BattleScreen battleScreen;
	public SpriteBatch batch;



	@Override
	public void create() {
		batch = new SpriteBatch();
		battleScreen = new BattleScreen(this);
		playScreen = new PlayScreen(this);
		setScreen(playScreen);

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void setBattleScreen() {
		setScreen(battleScreen);
	}

}