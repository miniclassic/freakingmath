package vn.pp.freakingadult.group;

import vn.pp.freakingadult.group.OptionGroup.OptionInterface;
import vn.pp.freakingadult.utils.MyPrefs;
import vn.pp.freakingadult.utils.Setting;

import com.badlogic.gdx.scenes.scene2d.Group;

public class LevelGroup extends Group implements OptionInterface{
	OptionGroup mEasy,mNormal,mHard;
	
	public static int LEVEL_EASY 	= 1;
	public static int LEVEL_NORMAL	= 2;
	public static int LEVEL_HARD	= 3;
	
	
	public LevelGroup(){
		super();
		this.setWidth(350);
		this.setHeight(100);
		
		init();
	}
	
	
	public void init(){
		mEasy=new OptionGroup(1,true, "easy");
		mEasy.setPosition(0, 0);
		mEasy.setListener(this);
		this.addActor(mEasy);
		
		mNormal=new OptionGroup(2,true, "normal");
		mNormal.setPosition(130, 0);
		mNormal.setListener(this);
		this.addActor(mNormal);
		
		mHard=new OptionGroup(3,true, "hard");
		mHard.setPosition(260, 0);
		mHard.setListener(this);
		this.addActor(mHard);
		
		setLevel(MyPrefs.getLevel());
	}


	@Override
	public void optionListener(int index) {
		// TODO Auto-generated method stub
		setLevel(index);
	}
	
	public void setLevel(int level){
		if(level==1){
			mEasy.setChoice(true);
			mNormal.setChoice(false);
			mHard.setChoice(false);
			MyPrefs.setLevel(LEVEL_EASY);
			Setting.configLevel=LEVEL_EASY;
//			MyPrefs.setLevel(1);
//			Setting.configLevel=1;
		}else if(level==2){
			mEasy.setChoice(false);
			mNormal.setChoice(true);
			mHard.setChoice(false);
			MyPrefs.setLevel(LEVEL_NORMAL);
			Setting.configLevel=LEVEL_NORMAL;
		}else if(level==3){
			mEasy.setChoice(false);
			mNormal.setChoice(false);
			mHard.setChoice(true);
			MyPrefs.setLevel(LEVEL_HARD);
			Setting.configLevel=LEVEL_HARD;
		}
	}
	
}