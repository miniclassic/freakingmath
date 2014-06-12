package vn.pp.freakingadult.assets;

import com.badlogic.gdx.audio.Sound;

public class MySound {
	public static Sound fail;
	public static Sound restart;
	public static Sound scored;	
	public static void setSoundandMusic(){
		fail=MyAssets.manager.get("data/sound/fail.ogg",Sound.class);
		restart=MyAssets.manager.get("data/sound/restart.ogg",Sound.class);
		scored=MyAssets.manager.get("data/sound/scored.ogg",Sound.class);
	}
}
