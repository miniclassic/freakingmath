package vn.pp.freakingadult.group;

import vn.pp.PPImage;
import vn.pp.freakingadult.BlockGame;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.utils.MyColor;
import vn.pp.freakingadult.utils.MyPrefs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class TimeGroup extends Group{
	public PPImage slide_bg,slide_bar,touch;
	public TextureRegion barRegion;
	Label maxTime,minTime,currTime;
	int currT;
	public TimeGroup(){
		super();
		
		this.setWidth(325);
		this.setHeight(40);
		
		init();
		
		
	}
	
	
	public void init(){
		slide_bg=new PPImage(MyAtlas.playGameAtlas.findRegion("slide_bg"));
		slide_bg.setPosition(0, 15);
		slide_bg.setTouchable(Touchable.disabled);
		this.addActor(slide_bg);
		
		barRegion=MyAtlas.playGameAtlas.findRegion("slide_bar");
//		barRegion.set
		barRegion.setRegion(0, 0, 0, 11);
		slide_bar=new PPImage(barRegion);
//		slide_bar.setTextureRegion(barRegion);
		slide_bar.setPosition(0, 15);
		slide_bar.setTouchable(Touchable.disabled);
		this.addActor(slide_bar);
		
		
		System.err.println("barRegion"+barRegion.getRegionWidth());
		System.err.println("slide_bar"+slide_bar.getWidth());
		
		touch=new PPImage(MyAtlas.playGameAtlas.findRegion("touch"));
		touch.setPosition(-20+MyPrefs.getTime(), -5);
//		touch.addListener(btnTouch);
		this.addActor(touch);
//		touch.addListener(dragListener);
		touch.setBounds(touch.getX(), touch.getY(), touch.getWidth(), touch.getHeight());
		
		dragListener.setTapSquareSize(2);
		touch.addListener(dragListener);
		
		LabelStyle lbStyle2=new LabelStyle(BlockGame.font50, Color.WHITE);
		maxTime=new Label("60second", lbStyle2);
		maxTime.setPosition(100, -45);
		maxTime.setFontScale(0.5f);
		maxTime.setAlignment(Align.right);
		maxTime.setColor(MyColor.color2);
		this.addActor(maxTime);
		
		minTime=new Label("1second", lbStyle2);
		minTime.setPosition(-10, -45);
		minTime.setFontScale(0.5f);
		minTime.setAlignment(Align.left);
		minTime.setColor(MyColor.color2);
		this.addActor(minTime);
		
		currTime=new Label("1 second", lbStyle2);
		currTime.setPosition(60, 25);
		currTime.setFontScale(0.6f);
		currTime.setAlignment(Align.center);
		currTime.setColor(MyColor.color8);
		this.addActor(currTime);
		
		
		updateCurrentTime(MyPrefs.getTime());
	}
	
	public void updateCurrentTime(float barWidth){
		float curr=barWidth/320f *60;
		
		
		currT=(int)(((int)(curr+2.5f)/5) *5);
		System.err.println("curr=="+currT);
		
		barRegion.setRegionWidth((int)barWidth+20);
		slide_bar.setWidth(barWidth);
		
		if(currT==0 && curr<=1.0) currT=1;
		else if(currT == 0 && curr<5.0) currT =2;
		currTime.setText(""+currT+" second");
		
	}
	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		
	}

	ClickListener btnTouch=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			System.out.println("---touchDown----x:"+x);
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer) {
			System.out.println("---x: "+x+ "--y: "+"---touch.getX():"+touch.getX());
			
			if(touch.getX()+touch.getWidth()/2<=0) touch.setX(-touch.getWidth()/2);
			else if(touch.getX()+touch.getWidth()/2>=320) touch.setX(320-touch.getWidth()/2);
			else 
				touch.setX(touch.getX()+x/2);
			
			updateCurrentTime((int)(touch.getX()+touch.getWidth()/2));
			super.touchDragged(event, x, y, pointer);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
		}
		
	};
	
	
	public int getCurrT(){
		if(currT==0) return 1;
		return currT;
	}

	DragListener dragListener = new DragListener() {
	    private float startDragX;

	    @Override
	    public void dragStart(
	            InputEvent event, 
	            float x, 
	            float y,
	            int pointer) {
	    	System.out.println("=======dragStart==========");
	        startDragX = x;
	    }

	    @Override
	    public void drag(InputEvent event, float x, float y, int pointer) {
	    	if(touch.getX()+touch.getWidth()/2<=0 && x - startDragX<=0) {
//	    		System.out.println("=======drag=======1===: "+x);
	    		touch.setX(-touch.getWidth()/2);
	    	}
			else if(touch.getX()+touch.getWidth()/2>=320 && x - startDragX>=0){
//				System.out.println("=======drag=======2===: "+x);
				touch.setX(320-touch.getWidth()/2);
			}
			else{
//				System.out.println("=======drag=======3===");
				touch.moveBy(x - startDragX, 0);
			}
	    	updateCurrentTime((int)(touch.getX()+touch.getWidth()/2));
	    }
	};
	
}
