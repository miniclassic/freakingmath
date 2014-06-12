package vn.pp.freakingadult.screens;


import vn.pp.freakingadult.assets.MyAssets;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.assets.MySound;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends BaseScreen{
	TextureAtlas atlas;
	Image imagebg;
	public LoadingScreen(){
		setActiveBackButton(false);
	}
	
	int timeRetro = 3000;
	long startTime = 0;
	int marginLogo = 30;
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		
		MyAssets.loadSoundGame();
		MyAssets.loadgame();
	}
	
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		MyAssets.manager.update();
		if (MyAssets.manager.getProgress() < 1.0) {
			
		} else {
			
				MyAtlas.setPlaygameAtlas();
				MySound.setSoundandMusic();
				
				ScreensManager.inst().loadingScreen=null;
				ScreensManager.inst().game.setScreen(ScreensManager.inst().getPlayGameScreen());
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
    

}
