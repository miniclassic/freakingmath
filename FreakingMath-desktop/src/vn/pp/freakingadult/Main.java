package vn.pp.freakingadult;

import vn.pp.ControlGGS;
import vn.pp.ControlHandler;
import vn.pp.freakingadult.BlockGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main implements ControlHandler, ControlGGS{
	static Main main;
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Freaking Math";
//		cfg.useGL20 = true; //false
		
//		cfg.width = 320;
//		cfg.height = 480;
		
		//800x1280
//		cfg.width = 400;
//		cfg.height = 640;
		
		//720x1280
		cfg.width = 360;
		cfg.height = 640;

//		cfg.width = 320;
//		cfg.height = 480;
		
		//1200x1920
//		cfg.width = 600;
//		cfg.height = 960;
		main=new Main();
		new LwjglApplication(new BlockGame(main, main), cfg);
	}

	@Override
	public void shareFb() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAd(boolean show) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFullAd(boolean show) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMoreApps() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shareTwitter() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void shareGplus() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void shareFbDetail(int level) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void shareGplusDetail(int level) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void showLoading() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void showToast(String text) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void showRateApps() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public boolean getSignedInGPGS() {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public void loginGPGS() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void submitScoreGPGS(int score) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void getLeaderboardGPGS() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void getAchievementsGPGS() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public boolean showExitAd(boolean show) {
	    // TODO Auto-generated method stub
	    return false;
	}
}
