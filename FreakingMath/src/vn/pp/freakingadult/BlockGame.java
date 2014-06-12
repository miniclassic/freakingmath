package vn.pp.freakingadult;

import vn.pp.ControlGGS;
import vn.pp.ControlHandler;
import vn.pp.freakingadult.screens.ScreensManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BlockGame extends Game{
	public static BitmapFont font50,font100,font100test, testfont;
	public ControlHandler controlHandler;
	public ControlGGS  controlGGS;
	
	public BlockGame(ControlHandler controlHandler, ControlGGS  controlGGS){
		super();
		this.controlHandler=controlHandler;
		this.controlGGS=controlGGS;
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		ScreensManager.inst().initialize(this);
		ScreensManager.inst().game.setScreen(ScreensManager.inst().getLoadingScreen());
		font50=new BitmapFont(Gdx.files.internal("data/font/font50.fnt"), Gdx.files.internal("data/font/font50.png"), false);
		font50.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		font100=new BitmapFont(Gdx.files.internal("data/font/font100.fnt"), Gdx.files.internal("data/font/font100.png"), false);
		font100.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
//		font100test=new BitmapFont(Gdx.files.internal("data/font/font100test.fnt"), Gdx.files.internal("data/font/font100test.png"), false);
//		font100test.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//		testfont=new BitmapFont(Gdx.files.internal("data/font/testfont.fnt"), Gdx.files.internal("data/font/testfont.png"), false);
//		testfont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void dispose() {
	    System.out.println("BlockGame----dispose()----");
		ScreensManager.inst().dispose();
		super.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	
}
