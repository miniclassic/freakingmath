package vn.pp;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PPCreateAnimations {
	public static Animation getAnimationFromMultiTextures(
			TextureAtlas atlas, String BaseName,
			int numberOfFrames, float frameDuration) {
		// Key frames list
		
		TextureRegion[] keyFrames = new TextureRegion[numberOfFrames];

		// Set key frames (each textures region from atlas)
		for (int i = 0; i < numberOfFrames; i++) {
					keyFrames[i] = atlas.findRegions(BaseName).get(i);
			}

		//
		Animation animation = new Animation(frameDuration, keyFrames);
		return animation;
	}
}
