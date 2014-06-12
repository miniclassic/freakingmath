package vn.pp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PPAnimation extends Image {

	// Animation for actor (Not mandatory)
	public Animation animation;
	public boolean isAnimationActive = false;
	public boolean killAllAnimations = false;
	public boolean isLopping = false;

	public PPAnimation(float posX, float posY, float width, float height) {
		super();
		setBounds(posX, posY, width, height);
		setPosition(posX, posY);
		setSize(width, height);
		setOrigin(getWidth() / 2, getHeight() / 2);

	}

	public PPAnimation() {
		super();
	}

	// Animation timer
	public float stateTime = 0;

	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta;

	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		if (isAnimationActive && animation != null) {
			
			// Get frame by frame and animate
			TextureRegion keyFrame = animation
					.getKeyFrame(stateTime, isLopping);
			// Draw it due to actors' settings
			batch.draw(keyFrame, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());

			if (animation.isAnimationFinished(stateTime)) {
				if (killAllAnimations) {
					isAnimationActive = false;
				}
			}
			
		}
		
	}

	public void setAnimation(Animation animation, boolean isLopping) {
		this.animation = animation;
		this.isLopping = isLopping;
		TextureRegion keyFrame = animation.getKeyFrame(0);
		setSize(keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
		isAnimationActive = true;

	}

	public boolean isKillAllAnimations() {
		return killAllAnimations;
	}

	/**
	 * Set killAllAnimations. If is true, after animations completed it wont be
	 * visible anymore
	 * */
	public void setKillAllAnimations(boolean killAllAnimations) {
		this.killAllAnimations = killAllAnimations;
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public void resetStatetime() {
		stateTime = 0;
	}

}
