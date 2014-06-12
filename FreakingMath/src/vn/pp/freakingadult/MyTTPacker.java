package vn.pp.freakingadult;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class MyTTPacker {
	public static void main(String[] args) {
		Settings tt = new Settings();
//		tt.maxWidth = 1024;
//		tt.maxHeight=2048;
		tt.maxWidth = 2048;
		tt.maxHeight=1024;
		tt.filterMin=TextureFilter.Linear;
		tt.filterMag=TextureFilter.Linear;
		TexturePacker2.process(tt, "../FreakingMath-android/data", "../FreakingMath-android/assets/data/datapacker/", "FileMoTa.txt");
//		TexturePacker2.process(tt, "../Block-android/imagedata/loading", "../Block-android/assets/data/loadingpacker/", "FileMoTa.txt");
	}
}
