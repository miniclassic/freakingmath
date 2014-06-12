package vn.pp.freakingadult.screens;

import vn.pp.freakingadult.group.MenuGroup;
import vn.pp.freakingadult.group.PlayGameGroup;
import vn.pp.freakingadult.group.ResultGroup;
import vn.pp.freakingadult.group.SettingGroup;
import vn.pp.freakingadult.utils.MyPrefs;
import vn.pp.freakingadult.utils.Setting;

import com.badlogic.gdx.Gdx;

public class PlayGameScreen extends BaseScreen {

	int currGroupShow = 0;
	
	final int GROUP_PLAY = 1;
	final int GROUP_MENU = 2;
	final int GROUP_SETTING = 3;
	final int GROUP_RESULT = 4;
	
	PlayGameGroup playGameGroup;
	MenuGroup menuGroup;
	ResultGroup resultGroup;
	SettingGroup settingGroup;
	int score=0;
	
	public boolean isStart=false;
	@Override
	public void show() {
		setActiveBackButton(true);
		// TODO Auto-generated method stub
		super.show();
		Setting.configTime = MyPrefs.getTime();

		float widthscreen = stage.getCamera().viewportWidth;
		float heightScreen = stage.getCamera().viewportHeight;
		playGameGroup=new PlayGameGroup(widthscreen, heightScreen);
		stage.addActor(playGameGroup);
		
		menuGroup=new MenuGroup();
		stage.addActor(menuGroup);
		
		currGroupShow = GROUP_MENU;
		
		settingGroup=new SettingGroup();
		stage.addActor(settingGroup);
		resultGroup=new ResultGroup(stage);
		stage.addActor(resultGroup);
		
//		if(!ScreensManager.inst().game.controlGGS.getSignedInGPGS()){
//		    ScreensManager.inst().game.controlGGS.loginGPGS();
//		}
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	}

	@Override
	public void resize(int w, int h) {
	    System.out.println("---resize in play screen----");
		super.resize(w, h);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		System.out.println("------keyBackPressed-------:"+currGroupShow);
		switch (currGroupShow) {
		case GROUP_MENU:
		    boolean result = ScreensManager.inst().game.controlHandler.showExitAd(true);
		    if(result){
			Gdx.app.exit();
		    }
			break;
		case GROUP_PLAY:
			if(score==0){
				ScreensManager.inst().playGameScreen.menuGame();
			}
			break;
		case GROUP_RESULT:
			resultGroup.moveOut();
			ScreensManager.inst().playGameScreen.menuGame();
			break;
		case GROUP_SETTING:
			settingGroup.moveOut();
			currGroupShow = GROUP_MENU;
//			ScreensManager.inst().playGameScreen.menuGame();
			break;
		default:
			break;
		}
	}
	
	public void startGame(){
		isStart=true;
		score=0;
		playGameGroup.initContent(0);
		currGroupShow = GROUP_PLAY;
	}
	
	public void menuGame(){
		isStart=false;
		menuGroup.moveIn();
		currGroupShow = GROUP_MENU;
	}
	
	public void setNext(){
		score++;
		playGameGroup.initContent(score);
	}
	
	public void setFail(){
		isStart=false;
		MyPrefs.setBestScore(score);
		resultGroup.setScore(score);
		resultGroup.moveIn();
		currGroupShow = GROUP_RESULT;
	} 
	
	public void setSetting(){
		isStart=false;
		settingGroup.moveIn();
		currGroupShow = GROUP_SETTING;
	}
	
	public void changeColor(){
		playGameGroup.changeColor();
	}
	
	public void resetGame(){
		isStart=false;
		menuGroup.resetGame();
	}
}
