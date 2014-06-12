package vn.pp.freakingadult.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyAtlas {
	public static TextureAtlas playGameAtlas;
	
	public static void setPlaygameAtlas(){
		if(MyAssets.manager.isLoaded("data/datapacker/FileMoTa.txt",TextureAtlas.class)){
			System.err.println("AAAAAAAAAA");
			playGameAtlas=MyAssets.manager.get("data/datapacker/FileMoTa.txt",TextureAtlas.class);
		}
	}
}
