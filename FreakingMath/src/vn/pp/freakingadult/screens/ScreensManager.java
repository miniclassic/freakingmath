package vn.pp.freakingadult.screens;

import vn.pp.freakingadult.BlockGame;
import vn.pp.freakingadult.assets.MyAssets;



public class ScreensManager {
	
	
	public static ScreensManager _instance;
	public static ScreensManager inst(){
		if(_instance==null){
			_instance=new ScreensManager();
		}
		return _instance;
	}
	
	
	public BlockGame game;
	
	public void initialize(BlockGame game) {
        this.game = game;
    }
	
	public PlayGameScreen playGameScreen;
	public LoadingScreen loadingScreen;
	public ScreensManager(){
		
	}
	
	
	
	
	public PlayGameScreen getPlayGameScreen(){
		if(playGameScreen==null){
			playGameScreen=new PlayGameScreen();
		}
		return playGameScreen;
	}
	
	public LoadingScreen getLoadingScreen(){
		if(loadingScreen==null){
			loadingScreen=new LoadingScreen();
		}
		return loadingScreen;
	}
	
	
	public void dispose() {
	    System.out.println("ScreensManager--------dispose====");
		if(playGameScreen!=null){
			playGameScreen=null;
		}
		if(loadingScreen!=null){
			loadingScreen = null;
		}
		MyAssets.manager.clear();
		
        _instance = null;
    } 
}
