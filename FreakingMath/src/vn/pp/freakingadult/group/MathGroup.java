package vn.pp.freakingadult.group;

import vn.pp.freakingadult.group.OptionGroup.OptionInterface;
import vn.pp.freakingadult.utils.MyPrefs;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MathGroup extends Group implements OptionInterface{
	public OptionGroup mCong,mTru,mNhan,mChia;
	
	public static int MATH_CONG 	= 1;
	public static int MATH_TRU	= 2;
	public static int MATH_NHAN	= 3;
	public static int MATH_CHIA	= 4;
	
	public MathGroup(){
		super();
		this.setWidth(350);
		this.setHeight(100);
		
		init();
	}
	
	
	public void init(){
		mCong=new OptionGroup(MATH_CONG,true, "+");
//		mCong=new OptionGroup(1,true, "+");
		mCong.setPosition(0, 0);
		mCong.setListener(this);
		this.addActor(mCong);
		mCong.setChoice(MyPrefs.getMath(MATH_CONG));
//		mCong.setChoice(MyPrefs.getMath(1));
		
		mTru=new OptionGroup(MATH_TRU,true, "-");
//		mTru=new OptionGroup(2,true, "-");
		mTru.setPosition(80, 0);
		mTru.setListener(this);
		this.addActor(mTru);
		mTru.setChoice(MyPrefs.getMath(MATH_TRU));
//		mTru.setChoice(MyPrefs.getMath(2));
		
		mNhan=new OptionGroup(MATH_NHAN,true, "x");
		mNhan=new OptionGroup(3,true, "x");
		mNhan.setPosition(170, 0);
		mNhan.setListener(this);
		this.addActor(mNhan);
		mNhan.setChoice(MyPrefs.getMath(MATH_NHAN));
//		mNhan.setChoice(MyPrefs.getMath(3));
		
		mChia=new OptionGroup(MATH_CHIA,true, ":");
//		mChia=new OptionGroup(4,true, "/");
		mChia.setPosition(260, 0);
		mChia.setListener(this);
		this.addActor(mChia);
		mChia.setChoice(MyPrefs.getMath(MATH_CHIA));
//		mChia.setChoice(MyPrefs.getMath(4));
		
		if(!MyPrefs.getMath(1) && !MyPrefs.getMath(2) && !MyPrefs.getMath(3) && !MyPrefs.getMath(4)){
			mCong.setChoice(true);
		}
	}


	@Override
	public void optionListener(int index) {
		// TODO Auto-generated method stub
		if(index==MATH_CONG){
//		    if(index==1){
			MyPrefs.setMath(index, mCong.getChoice());
		}
		else if(index==MATH_TRU){
//		    else if(index==2){
			MyPrefs.setMath(index, mTru.getChoice());
		}
		else if(index==MATH_NHAN){
//		    else if(index==3){
			MyPrefs.setMath(index, mNhan.getChoice());
		}
		else if(index==MATH_CHIA){
//		    else if(index==4){
			MyPrefs.setMath(index, mChia.getChoice());
		}
		
//		if(!MyPrefs.getMath(1) && !MyPrefs.getMath(2) && !MyPrefs.getMath(3) && !MyPrefs.getMath(4)){
//			MyPrefs.setMath(1, true);
//			mCong.setChoice(true);
//		}
	}
	
}
