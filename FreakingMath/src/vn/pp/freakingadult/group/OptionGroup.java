package vn.pp.freakingadult.group;

import vn.pp.PPImage;
import vn.pp.freakingadult.BlockGame;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.utils.MyColor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionGroup extends Group{
	
	public PPImage btnChoice;
	Label txtLabel;
	boolean isChoice=false;
	public int index;
	public interface OptionInterface{
		public void optionListener(int index);
	}
	
	public OptionInterface listener;
	
	public void setListener(OptionInterface _listener){
		listener=_listener;
	}
	
	public OptionGroup(int _index,boolean _isChoice,String label){
		super();
		isChoice=_isChoice;
		index=_index;
		this.setWidth(70);
		this.setHeight(100);
		
		LabelStyle lbStyle2=new LabelStyle(BlockGame.font50, Color.WHITE);
		txtLabel=new Label(label, lbStyle2);
		txtLabel.setFontScale(0.5f);
		txtLabel.setAlignment(Align.center);
//		txtLabel.setColor(236f/255, 133f/255, 41f/255, 1f);
//		txtLabel.setColor(MyColor.)
		txtLabel.setPosition(0, 0);
		txtLabel.setWidth(70);
		this.addActor(txtLabel);
		txtLabel.setTouchable(Touchable.disabled);
		
		btnChoice=new PPImage(MyAtlas.playGameAtlas.findRegion("on"));
		if(!isChoice){
			btnChoice.setTextureRegion(MyAtlas.playGameAtlas.findRegion("off"));
		}
		btnChoice.addListener(btnClick);
		btnChoice.setPosition(70/2-btnChoice.getWidth()/2, 40);
		this.addActor(btnChoice);
		
		setChoice(isChoice);
	}
	
	
	public void setChoice(boolean _isChoice){
		isChoice=_isChoice;
		if(!isChoice){
			btnChoice.setTextureRegion(MyAtlas.playGameAtlas.findRegion("off"));
			txtLabel.setColor(MyColor.color2);
		}else{
			btnChoice.setTextureRegion(MyAtlas.playGameAtlas.findRegion("on"));
			txtLabel.setColor(MyColor.color8);
		}
		
		
	}
	
	public boolean getChoice(){
		return isChoice;
	}
	
	
	ClickListener btnClick=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			// TODO Auto-generated method stub
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
			PPImage image=(PPImage)event.getTarget();
			if(image==btnChoice){
				setChoice(!isChoice);
				if(listener!=null){
					listener.optionListener(index);
				}
			}
		}
		
	};
	
}
