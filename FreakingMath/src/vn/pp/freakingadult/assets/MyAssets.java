package vn.pp.freakingadult.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class MyAssets {
	public static MyAssets _instance;
	public static MyAssets inst(){
		if(_instance==null){
			_instance=new MyAssets();
		}
		return _instance;
	}
	
	public static AssetManager manager=new AssetManager();
	
	public MyAssets(){
		manager=new AssetManager();
	}
	
	
	public static void loadgame(){
		manager.load("data/datapacker/FileMoTa.txt",TextureAtlas.class);
		
	}
	
	public static void loadSoundGame(){
		manager.load("data/sound/fail.ogg",Sound.class);
		manager.load("data/sound/restart.ogg",Sound.class);
		manager.load("data/sound/scored.ogg",Sound.class);
	}
	
	
}
