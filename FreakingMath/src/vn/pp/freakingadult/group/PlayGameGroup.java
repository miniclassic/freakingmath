package vn.pp.freakingadult.group;

import java.util.ArrayList;
import java.util.Random;

import vn.pp.PPImage;
import vn.pp.freakingadult.BlockGame;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.assets.MySound;
import vn.pp.freakingadult.screens.BaseScreen;
import vn.pp.freakingadult.screens.ScreensManager;
import vn.pp.freakingadult.utils.MyColor;
import vn.pp.freakingadult.utils.MyPrefs;
import vn.pp.freakingadult.utils.Setting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayGameGroup extends Group{
	PPImage imageBg;
	PPImage btnSound,btnTrue,btnFalse;
	PPImage timePlay;

	Label txtScore;
	Label txtMath,txtResult;
	String strMath,strResult;
	int isTrue;

	float scaleFont = 1.3f;
	public PlayGameGroup(float widthscreen, float heightScreen){
		super();

		this.setWidth(BaseScreen.VIEWPORT_WIDTH);
		this.setHeight(BaseScreen.VIEWPORT_HEIGHT);

		initMenu(widthscreen, heightScreen);

		//		initContent(0);
		int heigtTimeImage = 10;
		timePlay=new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		timePlay.setWidth(widthscreen);
		//		timePlay.setWidth(BaseScreen.VIEWPORT_WIDTH);
		timePlay.setHeight(heigtTimeImage);
		timePlay.setPosition( (1f*BaseScreen.VIEWPORT_WIDTH - widthscreen) /2, 
				heightScreen-heigtTimeImage - (heightScreen-BaseScreen.VIEWPORT_HEIGHT)/2);
		//		BaseScreen.VIEWPORT_HEIGHT-heigtTimeImage);
		//		timePlay.setColor(Color.BLACK);
		//		timePlay.setColor(Color.valueOf("f1f1f1ff"));
		timePlay.setOriginX(0);
		this.addActor(timePlay);
		//System.out.println("---widthscreen: "+widthscreen+"---heightScreen:"+heightScreen);
	}

	public void initMenu(float widthscreen, float heightScreen){

		//		if(MyPrefs.get)

		imageBg=new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(widthscreen);
		imageBg.setHeight(heightScreen);
		imageBg.setPosition( -(widthscreen - BaseScreen.VIEWPORT_WIDTH),
				-(heightScreen-BaseScreen.VIEWPORT_HEIGHT)/2);
		imageBg.setColor(MyColor.getPlayColor());
		this.addActor(imageBg);
		//		imageBg.setColor(Color.GREEN);

		btnSound=new PPImage(MyAtlas.playGameAtlas.findRegion("sound_on"));
		//		btnSound.setPosition(20, -50);
		btnSound.setPosition(20, 700);
		btnSound.setScale(0.8f);
		btnSound.addListener(btnClick);
		if(!MyPrefs.getSound()){
			btnSound.setTextureRegion(MyAtlas.playGameAtlas.findRegion("sound_off"));
		}
		this.addActor(btnSound);


		btnTrue=new PPImage(MyAtlas.playGameAtlas.findRegion("true"));
		btnTrue.setY(40);
		btnTrue.setX(BaseScreen.VIEWPORT_WIDTH/2-btnTrue.getWidth()-20);
		MyClickListener clickTrue = new MyClickListener(new Runnable() {
			public void run() {
				if(ScreensManager.inst().playGameScreen.isStart){
//					btnTrue.setTextureRegion(MyAtlas.playGameAtlas.findRegion("true"));
					if(isTrue==1){
						ScreensManager.inst().playGameScreen.setNext();
						if(MyPrefs.getSound()){
							MySound.scored.play();
						}
						setTimePlay();
					}else{
						ScreensManager.inst().playGameScreen.setFail();
						if(MyPrefs.getSound()){
							MySound.fail.play();
						}
						timePlay.clearActions();
					}
				}
			}
		});
		btnTrue.addListener(clickTrue);
		//		btnTrue.addListener(btnClick);
		this.addActor(btnTrue);


		btnFalse=new PPImage(MyAtlas.playGameAtlas.findRegion("false"));
		btnFalse.setY(40);
		btnFalse.setX(BaseScreen.VIEWPORT_WIDTH/2+20);
		MyClickListener clickFalse= new MyClickListener(new Runnable() {
			public void run() {
				if(ScreensManager.inst().playGameScreen.isStart){
					btnFalse.setTextureRegion(MyAtlas.playGameAtlas.findRegion("false"));
					if(isTrue==1){
						ScreensManager.inst().playGameScreen.setFail();
						if(MyPrefs.getSound()){
							MySound.fail.play();
						}
						timePlay.clearActions();
					}else{
						ScreensManager.inst().playGameScreen.setNext();
						if(MyPrefs.getSound()){
							MySound.scored.play();
						}
						setTimePlay();
					}
				}
			}
		});
		btnFalse.addListener(clickFalse);
		//		btnFalse.addListener(btnClick);
		this.addActor(btnFalse);

		LabelStyle lbStyle2=new LabelStyle(BlockGame.font50, Color.WHITE);
		txtScore=new Label("", lbStyle2);
		txtScore.setAlignment(Align.right);
		txtScore.setPosition(445, 740);
		txtScore.setFontScale(1.4f);
		this.addActor(txtScore);


		LabelStyle lbStyle=new LabelStyle(BlockGame.font100, Color.WHITE);
		txtMath=new Label(""+strMath, lbStyle);
		txtMath.setAlignment(Align.center);
		txtMath.setWidth(300);
		txtMath.setPosition(90, 500);
		txtMath.setFontScale(scaleFont);
		this.addActor(txtMath);

		txtResult=new Label(""+strResult, lbStyle);
		txtResult.setAlignment(Align.center);
		txtResult.setWidth(300);
		txtResult.setPosition(90, 350);
		txtResult.setFontScale(scaleFont);
		this.addActor(txtResult);
	}


	public void initContent(int score){

		//		initMathOld(score);
		createMath(score);
		txtScore.setText(""+score);
		txtMath.setText(strMath);
		txtResult.setText(strResult);
		timePlay.setColor(Color.valueOf("f1f1f1"));
		//		timePlay.setColor(Color.WHITE);
		timePlay.setScale(1f, 1f);
	}


	ClickListener btnClick=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			// TODO Auto-generated method stub
			PPImage image=(PPImage)event.getTarget();
			if(image==btnSound){
				//				btnSound.setTextureRegion(MyAtlas.playGameAtlas.findRegion("sound_off"));
			}else if(image==btnTrue){
				if(ScreensManager.inst().playGameScreen.isStart){
					//				btnTrue.setTextureRegion(MyAtlas.playGameAtlas.findRegion("true_select"));
				}
			}else if(image==btnFalse){
				if(ScreensManager.inst().playGameScreen.isStart){
					//				btnFalse.setTextureRegion(MyAtlas.playGameAtlas.findRegion("false_select"));
				}
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
			PPImage image=(PPImage)event.getTarget();
			if(image==btnSound){
				if(MyPrefs.getSound()){
					MyPrefs.setSound(false);
					btnSound.setTextureRegion(MyAtlas.playGameAtlas.findRegion("sound_off"));
				}else{
					MyPrefs.setSound(true);
					btnSound.setTextureRegion(MyAtlas.playGameAtlas.findRegion("sound_on"));
				}
			}else if(image==btnTrue){
				if(ScreensManager.inst().playGameScreen.isStart){
					btnTrue.setTextureRegion(MyAtlas.playGameAtlas.findRegion("true"));
					if(isTrue==1){
						ScreensManager.inst().playGameScreen.setNext();
						if(MyPrefs.getSound()){
							MySound.scored.play();
						}
						setTimePlay();
					}else{
						ScreensManager.inst().playGameScreen.setFail();
						if(MyPrefs.getSound()){
							MySound.fail.play();
						}
						timePlay.clearActions();
					}
				}
			}else if(image==btnFalse){
				if(ScreensManager.inst().playGameScreen.isStart){
					btnFalse.setTextureRegion(MyAtlas.playGameAtlas.findRegion("false"));
					if(isTrue==1){
						ScreensManager.inst().playGameScreen.setFail();
						if(MyPrefs.getSound()){
							MySound.fail.play();
						}
						timePlay.clearActions();
					}else{
						ScreensManager.inst().playGameScreen.setNext();
						if(MyPrefs.getSound()){
							MySound.scored.play();
						}
						setTimePlay();
					}
				}
			}
		}

	};


	public void setTimePlay(){
		//	    timePlay.setColor(Color.WHITE);
		timePlay.clearActions();
		float timeRight = (1f*Setting.configTime)*0.7f ;
		//	    int timeLeft = 0;

		System.out.println("----Setting.configTime: "+Setting.configTime);
		//System.out.println("----timeNormal: "+timeRight);
		timePlay.addAction(Actions.sequence(
				Actions.scaleTo(1f, 1f),
				Actions.scaleTo(0.3f, 1f, timeRight),
				Actions.run(new Runnable() {
					public void run() {
						timePlay.setColor(Color.RED);
					}
				}),
				Actions.scaleTo(0f, 1f,Setting.configTime - timeRight),
				Actions.run(new Runnable() {
					public void run() {
						ScreensManager.inst().playGameScreen.setFail();
						if(MyPrefs.getSound()){
							MySound.fail.play();
						}
					}
				})));

		//	    timePlay.addAction(Actions.sequence(
		//		    Actions.scaleTo(1f, 1f),
		//		    Actions.scaleTo(0f, 1f,Setting.configTime),
		//		    Actions.run(new Runnable() {
		//			public void run() {
		//			    ScreensManager.inst().playGameScreen.setFail();
		//			    if(MyPrefs.getSound()){
		//				MySound.fail.play();
		//			    }
		//			}
		//		    })));
	}


	public void initMathabc(int score){
		//Random x,y trong khoang theo diem
		int x,y,result,math;

		if(score<2){
			//xy<5 va chi co phep cong tru
			x=new Random().nextInt(5)+1;
			y=new Random().nextInt(5)+1;
			math=new Random().nextInt(2)+1;
		}else if(score<5){
			//xy<5 va chi co phep cong tru
			x=new Random().nextInt(10)+1;
			y=new Random().nextInt(10)+1;
			math=new Random().nextInt(2)+1;
		}else if(score<10){
			x=new Random().nextInt(10+score*5)+1;
			math=new Random().nextInt(3)+1;
			if(math==3){
				y=new Random().nextInt(10)+1;
			}else{
				y=new Random().nextInt(10+score*5)+1;
			}
		}else{
			x=new Random().nextInt(100)+1;
			math=new Random().nextInt(4)+1;
			if(math==3 || math==4){
				y=new Random().nextInt(20)+1;
			}else{
				y=new Random().nextInt(100)+1;
			}
		}
		int xtruoc=new Random().nextInt(2);
		isTrue=new Random().nextInt(2);
		if(math==1){
			//Phep cong
			if(xtruoc==1){
				strMath=x+" + "+y;
			}else{
				strMath=y+" + "+x;
			}

			if(isTrue==1){
				strResult="= "+(x+y);
			}else{
				int rd=new Random().nextInt(x+y)-(x+y/2);
				if(rd==0) rd=1;
				result=x+y+rd;
				strResult="= "+result;
			}
		}else if(math==2){//tru
			if(x>y){
				strMath=x+" - "+y;
			}else{
				strMath=y+" - "+x;
			}

			if(isTrue==1){
				strResult="= "+(Math.abs(x-y));
			}else{
				if(Math.abs(x-y)==0){
					strResult="= "+(x+y);
				}else{
					int rd=new Random().nextInt(x+y);
					if(rd==Math.abs(x-y)) rd=1;
					result=rd;
					strResult="= "+rd;
				}
			}
		}else if(math==3){
			//Nhan
			if(xtruoc==1){
				strMath=x+" * "+y;
			}else{
				strMath=y+" * "+x;
			}

			if(isTrue==1){
				strResult="= "+(x+y);
			}else{
				int rd=new Random().nextInt(x*y)-(x*y/2);
				if(rd==0) rd=1;
				result=x*y+rd;
				strResult="= "+result;
			}
		}else if(math==4){
			//Chia
			if(isTrue==1){
				result=x*y;

				strMath=result+" / "+x;
				strResult="= "+y;
			}else{
				result=x*y;

				strMath=result+" / "+x;

				int rd=new Random().nextInt(y)-(y/2);
				if(rd==0) rd=1;
				result=rd+y;
				strResult="= "+result;
			}
		}

	}

	public void initMathOld(int score){
		ArrayList<Integer> listMath=new ArrayList<Integer>();
		for(int i=0;i<4;i++){
			if(MyPrefs.getMath(i+1)){
				listMath.add(i+1);
			}
		}
		if(listMath.size()==0){
			MyPrefs.setMath(1, true);
			listMath.add(1);
		}
		//System.out.println("---listMath.size(): "+listMath.size());
		int rdMath = new Random().nextInt(listMath.size());
		int x,y,result,math;
		int xtruoc=new Random().nextInt(2);
		isTrue=new Random().nextInt(2);


		if(score<2){
			//xy<5 va chi co phep cong tru
			x=new Random().nextInt(5)+1;
			y=new Random().nextInt(5)+1;
		}else if(score<5){
			//xy<5 va chi co phep cong tru
			x=new Random().nextInt(10)+1;
			y=new Random().nextInt(10)+1;
		}else if(score<10){
			x=new Random().nextInt(10+score*5)+1;
			y=new Random().nextInt(10+score*5)+1;
			if(listMath.get(rdMath)==3 || listMath.get(rdMath)==4){
				y=new Random().nextInt(10)+1;
			}else{
				y=new Random().nextInt(10+score*5)+1;
			}
		}else{
			x=new Random().nextInt(100)+1;
			if(listMath.get(rdMath)==3 || listMath.get(rdMath)==4){
				y=new Random().nextInt(20)+1;
			}else{
				y=new Random().nextInt(100)+1;
			}
		}

		//cong
		if(listMath.get(rdMath)==1){
			if(xtruoc==1){
				strMath=x+" + "+y;
			}else{
				strMath=y+" + "+x;
			}

			if(isTrue==1){
				strResult="= "+(x+y);
			}else{
				int rd=new Random().nextInt(x+y)-(x+y/2);
				if(rd==0) rd=1;
				result=x+y+rd;
				strResult="= "+result;
			}
		}
		//tru
		else if(listMath.get(rdMath)==2){
			if(x>y){
				strMath=x+" - "+y;
			}else{
				strMath=y+" - "+x;
			}

			if(isTrue==1){
				strResult="= "+(Math.abs(x-y));
			}else{
				if(Math.abs(x-y)==0){
					strResult="= "+(x+y);
				}else{
					int rd=new Random().nextInt(x+y);
					if(rd==Math.abs(x-y)) rd=1;
					result=rd;
					strResult="= "+rd;
				}
			}
		}
		//Nhan
		else if(listMath.get(rdMath)==3){
			if(xtruoc==1){
				strMath=x+" x "+y;
			}else{
				strMath=y+" x "+x;
			}

			if(isTrue==1){
				strResult="= "+(x*y);
			}else{
				int rd=new Random().nextInt(x*y)-(x*y/2);
				if(rd==0) rd=1;
				result=x*y+rd;
				strResult="= "+result;
			}
		}
		//Chia
		else if(listMath.get(rdMath)==4){
			if(isTrue==1){
				result=x*y;

				strMath=result+" / "+x;
				strResult="= "+y;
			}else{
				result=x*y;

				strMath=result+" / "+x;

				int rd=new Random().nextInt(y)-(y/2);
				if(rd==0) rd=1;
				result=rd+y;
				strResult="= "+result;
			}
		}
	}

	public void changeColor(){
		imageBg.setColor(MyColor.getPlayColor());
	}

	int x,y,result;
	public void createMath(int score){
	    Random random = new Random();
		ArrayList<Integer> listMath=new ArrayList<Integer>();
		for(int i=0;i<4;i++){
			if(MyPrefs.getMath(i+1)){
				listMath.add(i+1);
			}
		}

		int rdMath = new Random().nextInt(listMath.size());
		int level = Setting.configLevel;
		float time = Setting.configTime;

		int xtruoc=random.nextInt(2);
		isTrue=random.nextInt(2);

		if(listMath.get(rdMath)==MathGroup.MATH_NHAN){
			createHardNhan(time, level);		    
		}
		else if(listMath.get(rdMath)==MathGroup.MATH_CHIA){
			createHardChia(time, level);
		}
		else if(listMath.get(rdMath)==MathGroup.MATH_CONG){
		    createHardCong(time, level, score);
		}
		else if(listMath.get(rdMath)==MathGroup.MATH_TRU){
			createHardTru(time, level);
		}

		//cong
		if(listMath.get(rdMath)==1){
			if(xtruoc==1){
				strMath=x+" + "+y;
			}else{
				strMath=y+" + "+x;
			}

			if(isTrue==1){
				strResult="= "+(x+y);
			}else{
				int rd=random.nextInt(x+y)-(x+y/2);
				if(rd==0) rd=1;
				result=x+y+rd;
				strResult="= "+result;
			}
		}
		//tru
		else if(listMath.get(rdMath)==2){
			if(x>y){
				strMath=x+" - "+y;
			}else{
				strMath=y+" - "+x;
			}

			if(isTrue==1){
				strResult="= "+(Math.abs(x-y));
			}else{
				if(Math.abs(x-y)==0){
					strResult="= "+(x+y);
				}else{
					int rd=random.nextInt(x+y);
					if(rd==Math.abs(x-y)) rd=1;
					result=rd;
					strResult="= "+rd;
				}
			}
		}
		//Nhan
		else if(listMath.get(rdMath)==3){
			if(xtruoc==1){
				strMath=x+" x "+y;
			}else{
				strMath=y+" x "+x;
			}

			if(isTrue==1){
				strResult="= "+(x*y);
			}else{
				int rd=random.nextInt(x*y)-(x*y/2);
				if(rd==0) rd=1;
				result=x*y+rd;
				strResult="= "+result;
			}
		}
		//Chia
		else if(listMath.get(rdMath)==4){
			if(isTrue==1){
				result=x*y;

				strMath=result+" : "+x;
				strResult="= "+y;
			}else{
				result=x*y;

				strMath=result+" : "+x;

				int rd=random.nextInt(y)-(y/2);
				if(rd==0) rd=1;
				result=rd+y;
				strResult="= "+result;
			}
		}
	}

	private void createHardNhan(float time, int level) {
		//	    int x, y;
		if(time<=5){
			x=new Random().nextInt(10);
			if(level==LevelGroup.LEVEL_HARD){
				y=new Random().nextInt(10);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				y=new Random().nextInt(7);
			} 
			else {
				y=new Random().nextInt(5);
			}
		}
		else if(time<=30){
			x=new Random().nextInt(20);
			if(level==LevelGroup.LEVEL_HARD){
				y=new Random().nextInt(20);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				y=new Random().nextInt(14);
			} 
			else {
				y=new Random().nextInt(10);
			}
		} 
		else {
			x=new Random().nextInt(30);
			if(x<=10){
				if(level==LevelGroup.LEVEL_HARD){
					y=new Random().nextInt(99);
				}
				else if(level==LevelGroup.LEVEL_NORMAL){
					y=new Random().nextInt(60);
				} 
				else {
					y=new Random().nextInt(30);
				}
			} 
			else if(x<=20){
				if(level==LevelGroup.LEVEL_HARD){
					y=new Random().nextInt(50);
				}
				else if(level==LevelGroup.LEVEL_NORMAL){
					y=new Random().nextInt(30);
				} 
				else {
					y=new Random().nextInt(10);
				}
			}
			else {
				if(level==LevelGroup.LEVEL_HARD){
					y=new Random().nextInt(30);
				}
				else if(level==LevelGroup.LEVEL_NORMAL){
					y=new Random().nextInt(20);
				} 
				else {
					y=new Random().nextInt(1);
				}
			}
		}
	}

	private void createHardChia(float time, int level) {
		//	    int x, y;
		if(time<=5){
			x=new Random().nextInt(20);
			if(level==LevelGroup.LEVEL_HARD){
				y=new Random().nextInt(10-1)+1;
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				y=new Random().nextInt(7-1)+1;
			} 
			else {
				y=new Random().nextInt(5-1)+1;
			}
		}
		else if(time<=30){
			if(level==LevelGroup.LEVEL_HARD){
				x=new Random().nextInt(99);
				y=new Random().nextInt(20-1)+1;
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=new Random().nextInt(70);
				y=new Random().nextInt(7-1)+1;
			} 
			else {
				x=new Random().nextInt(50);
				y=new Random().nextInt(5-1)+1;
			}
		} 
		// time >30
		else {
			if(level==LevelGroup.LEVEL_HARD){
				x=new Random().nextInt(100);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=new Random().nextInt(70);
			} 
			else {
				x=new Random().nextInt(50);
			}
			if(x<=30){
				y=new Random().nextInt(10-1)+1;
			} 
			else if(x<=70){
				y=new Random().nextInt(20-1)+1;
			}
			else {
				y=new Random().nextInt(30-1)+1;
			}
		}
	}

	int scopeRandom = 0;
	int scopeEasy 	= 10;
	int scopeNormal = 15;
	int scopeHard	= 20;
	private void createHardCong(float time, int level, int score) {
	    Random random = new Random();
		//	    int x, y;
		if(time<=5){
			if(level==LevelGroup.LEVEL_HARD){
				x=random.nextInt(30)+1;
				y=random.nextInt(30)+1;
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=random.nextInt(20)+1;
				y=random.nextInt(20)+1;
			} 
			else {
			    if(score>30){
				scopeRandom = scopeHard;
			    }
			    else if(score>15){
				scopeRandom = scopeNormal;
			    } else {
				scopeRandom = scopeEasy;
			    }
			    x=random.nextInt(scopeRandom)+1;
			    y=random.nextInt(scopeRandom)+1;
//				x=random.nextInt(10)+1;
//				y=random.nextInt(10)+1;
			}
		}
		else if(time<=30){
			if(level==LevelGroup.LEVEL_HARD){
				x=random.nextInt(60)+1;
				y=random.nextInt(60)+1;
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=random.nextInt(40)+1;
				y=random.nextInt(40)+1;
			} 
			else {
				x=random.nextInt(20)+1;
				y=random.nextInt(20)+1;
			}
		} 
		else {
			if(level==LevelGroup.LEVEL_HARD){
				x=random.nextInt(100)+1;
				y=random.nextInt(100)+1;
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=random.nextInt(70)+1;
				y=random.nextInt(70)+1;
			} 
			else {
				x=random.nextInt(50)+1;
				y=random.nextInt(50)+1;
			}
		}
	}

	private void createHardTru(float time, int level) {
		//	    int x, y;
		if(time<=5){
			if(level==LevelGroup.LEVEL_HARD){
				x=new Random().nextInt(30);
				y=new Random().nextInt(30);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=new Random().nextInt(20);
				y=new Random().nextInt(20);
			} 
			else {
				x=new Random().nextInt(10);
				y=new Random().nextInt(10);
			}
		}
		else if(time<=30){
			if(level==LevelGroup.LEVEL_HARD){
				x=new Random().nextInt(60);
				y=new Random().nextInt(60);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=new Random().nextInt(40);
				y=new Random().nextInt(40);
			} 
			else {
				x=new Random().nextInt(20);
				y=new Random().nextInt(20);
			}
		} 
		else {
			if(level==LevelGroup.LEVEL_HARD){
				x=new Random().nextInt(100);
				y=new Random().nextInt(100);
			}
			else if(level==LevelGroup.LEVEL_NORMAL){
				x=new Random().nextInt(70);
				y=new Random().nextInt(70);
			} 
			else {
				x=new Random().nextInt(50);
				y=new Random().nextInt(50);
			}
		}
	}
}
