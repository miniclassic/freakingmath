package vn.pp.freakingadult.group;

import vn.pp.PPImage;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.screens.BaseScreen;
import vn.pp.freakingadult.utils.MyPrefs;
import vn.pp.freakingadult.utils.Setting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingGroup extends Group{
	public PPImage imageBg;
	public PPImage popup;
	public PPImage btnOk;
	public Label txtScore,txtBestScore;
	MathGroup mathGroup;
	TimeGroup timeGroup;
	LevelGroup levelGroup;
	public SettingGroup(){
		super();
		this.setWidth(BaseScreen.VIEWPORT_WIDTH);
		this.setHeight(BaseScreen.VIEWPORT_HEIGHT);
		
		
		init();
		this.setPosition(0, BaseScreen.VIEWPORT_HEIGHT+100);
	}
	
	public void init(){
	    int margintop = 50;
		imageBg=new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(BaseScreen.VIEWPORT_WIDTH+200);
		imageBg.setHeight(BaseScreen.VIEWPORT_HEIGHT+200);
		imageBg.setPosition(-100, -100);
		this.addActor(imageBg);
		imageBg.setColor(new Color(0f/255, 0f/255, 0f/255, 0/255));
		
		popup=new PPImage(MyAtlas.playGameAtlas.findRegion("setting"));
		popup.setX(BaseScreen.VIEWPORT_WIDTH/2-popup.getWidth()/2);
//		popup.setY(150);
		popup.setY(250 - margintop);
		this.addActor(popup);
		
		
		btnOk=new PPImage(MyAtlas.playGameAtlas.findRegion("ok"));
		btnOk.setX(BaseScreen.VIEWPORT_WIDTH/2-btnOk.getWidth()/2);
		btnOk.setY(150 - margintop);
		this.addActor(btnOk);
		MyClickListener listenerOk = new MyClickListener(new Runnable() {
		    public void run() {
			if(!MyPrefs.getMath(1) && !MyPrefs.getMath(2) && !MyPrefs.getMath(3) && !MyPrefs.getMath(4)
					|| mathGroup.mCong.isChoice){
			    MyPrefs.setMath(1, true);
			}
			btnOk.setTextureRegion(MyAtlas.playGameAtlas.findRegion("ok"));
			moveOut();
		    }
		});
		btnOk.addListener(listenerOk);
//		btnOk.addListener(btnClick);
		
		mathGroup=new MathGroup();
		mathGroup.setPosition(80, 510 - margintop);
		this.addActor(mathGroup);
		
		levelGroup=new LevelGroup();
		levelGroup.setPosition(80, 380 - margintop);
		this.addActor(levelGroup);
		
		timeGroup=new TimeGroup();
		timeGroup.setPosition(80, 310 - margintop);
		this.addActor(timeGroup);
	}
	
	
	public void setScore(int score){
		txtScore.setText(""+score);
		txtBestScore.setText(""+MyPrefs.getBestScore());
	}

	ClickListener btnClick=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			// TODO Auto-generated method stub
			PPImage image=(PPImage)event.getTarget();
			if(image==btnOk){
//				btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play_select"));
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
			PPImage image=(PPImage)event.getTarget();
			if(image==btnOk){
				btnOk.setTextureRegion(MyAtlas.playGameAtlas.findRegion("ok"));
//				SettingGroup.this.remove();
				moveOut();
			}
		}
		
	};
	
	public void moveIn(){
		imageBg.getColor().a=0f;
		this.addAction(Actions.sequence(Actions.moveTo(this.getX(), BaseScreen.VIEWPORT_HEIGHT+100 ),Actions.moveTo( 0 , 0 ,0.5f),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				imageBg.getColor().a=160f/255;
			}
		})));
	}
	
	public void moveOut(){
		imageBg.getColor().a=0f;
		this.addAction(Actions.sequence(Actions.moveTo( 0 , 0 ),Actions.moveTo(this.getX(), BaseScreen.VIEWPORT_HEIGHT+100 ,0.5f),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Setting.configTime=timeGroup.getCurrT();
				MyPrefs.setTime(Setting.configTime);
			}
		})));
	}
}
