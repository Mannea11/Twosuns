package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import MainChar.MainCharWalking;
import Scenes.MainMenu;
import Transitions.HorizontalSlicingTransition;
import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.transition.ScreenTransition;

public class TwoSuns extends ManagedGame<ManagedScreen, ScreenTransition> {

	public SpriteBatch batch;
	private InputMultiplexer inputMultiplexer;
	@Override
	public void create() {
		super.create();

		inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		batch = new SpriteBatch();
		this.screenManager.addScreen("PlayScreen", new PlayScreen(this));
		this.screenManager.addScreen("BattleScreen",new BattleScreen(this));
		this.screenManager.addScreen("MainMenu",new MainMenu(this));

		HorizontalSlicingTransition Horizontal = new HorizontalSlicingTransition(batch,10,1);
		screenManager.addScreenTransition("Horizontal", Horizontal);

		this.screenManager.pushScreen("BattleScreen", null);

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public void addInputProcessor(InputProcessor processor) {
		inputMultiplexer.addProcessor(processor);
	}

	public void removeInputProcessor(InputProcessor processor) {
		inputMultiplexer.removeProcessor(processor);
	}
}