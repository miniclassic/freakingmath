package vn.pp.freakingadult.group;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import vn.pp.PPImage;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.screens.BaseScreen;
import vn.pp.freakingadult.screens.ScreensManager;
import vn.pp.freakingadult.utils.MyPrefs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuGroup extends Group{
	public PPImage imageBg;
	public PPImage logo;
	public PPImage btnPlay,btnRate,btnHighScore;

	Label lbtest;
	BitmapFont font;
	public static final String FONT_CHARACTERS = "0123456789/-+=*x";
	
	public MenuGroup(){
	    super();
	    this.setWidth(BaseScreen.VIEWPORT_WIDTH);
	    this.setHeight(BaseScreen.VIEWPORT_HEIGHT);

	    init();
	}
	
	public void init(){
		imageBg=new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(BaseScreen.VIEWPORT_WIDTH+200);
		imageBg.setHeight(BaseScreen.VIEWPORT_HEIGHT+200);
		imageBg.setPosition(-100, -100);
		this.addActor(imageBg);
		imageBg.setColor(new Color(196f/255, 63f/255, 63f/255, 1f));
		
		
		logo=new PPImage(MyAtlas.playGameAtlas.findRegion("logo"));
		logo.setX(BaseScreen.VIEWPORT_WIDTH/2-logo.getWidth()/2);
		logo.setY(480);
		this.addActor(logo);
		
		
		btnPlay=new PPImage(MyAtlas.playGameAtlas.findRegion("play"));
		btnPlay.setX(BaseScreen.VIEWPORT_WIDTH/2-btnPlay.getWidth()/2);
		btnPlay.setY(255);
		this.addActor(btnPlay);
		MyClickListener listenerPlay = new MyClickListener(new Runnable() {
		    public void run() {
//			System.out.println("----12c------");
			//check have setting
			if(!MyPrefs.getMath(1) && !MyPrefs.getMath(2) && !MyPrefs.getMath(3) && !MyPrefs.getMath(4)  ){
//			    System.out.println("----abc------");
//			    btnHighScore.setTextureRegion(MyAtlas.playGameAtlas.findRegion("highscore"));
//			    ScreensManager.inst().playGameScreen.setSetting();
//			    ScreensManager.inst().game.controlHandler.showToast("Please chose math for play!");
			    MyPrefs.setMath(1, true);
			} 
//			else {
			    btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play"));
			    ScreensManager.inst().playGameScreen.changeColor();
			    ScreensManager.inst().playGameScreen.startGame();
			    moveOut();
//			}
		    }
		});
		btnPlay.addListener(listenerPlay);
//		btnPlay.addListener(btnClick);
		
		btnRate=new PPImage(MyAtlas.playGameAtlas.findRegion("rate"));
		btnRate.setX(BaseScreen.VIEWPORT_WIDTH/2-15-btnRate.getWidth());
		btnRate.setY(175);
		this.addActor(btnRate);
		MyClickListener listenerRate= new MyClickListener(new Runnable() {
		    public void run() {
//			btnRate.setTextureRegion(MyAtlas.playGameAtlas.findRegion("rate"));
			ScreensManager.inst().game.controlHandler.showRateApps();
//			ScreensManager.inst().game.controlHandler.showMoreApps();
		    }
		});
		btnRate.addListener(listenerRate);
//		btnRate.addListener(btnClick);
		
		btnHighScore=new PPImage(MyAtlas.playGameAtlas.findRegion("highscore"));
		btnHighScore.setX(BaseScreen.VIEWPORT_WIDTH/2+15);
		btnHighScore.setY(175);
		this.addActor(btnHighScore);
		MyClickListener listenerHScore= new MyClickListener(new Runnable() {
		    public void run() {
			ScreensManager.inst().game.controlGGS.getLeaderboardGPGS();
//			
//			btnHighScore.setTextureRegion(MyAtlas.playGameAtlas.findRegion("highscore"));
//			ScreensManager.inst().playGameScreen.setSetting();
		    }
		});
		btnHighScore.addListener(listenerHScore);
//		btnHighScore.addListener(btnClick);
		
	}
	
	int moveNum = 7;
	ClickListener btnClick=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			// TODO Auto-generated method stub
			PPImage image=(PPImage)event.getTarget();
			image.moveBy(0, -moveNum);
//			if(image==btnPlay){
//			    btnPlay.moveBy(0, -moveNum);
////				btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play_select"));
//			}else if(image==btnRate){
//			    btnRate.moveBy(0, -moveNum);
////				btnRate.setTextureRegion(MyAtlas.playGameAtlas.findRegion("rate_select"));
//			}else if(image==btnHighScore){
//			    btnHighScore.moveBy(0, -moveNum);
////				btnHighScore.setTextureRegion(MyAtlas.playGameAtlas.findRegion("rank_select"));
//			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
			PPImage image=(PPImage)event.getTarget();
			if(image==btnPlay){
			    btnPlay.addAction(sequence( run(new Runnable() {
				public void run() {
				    btnPlay.moveBy(0f, 7f);
				}
			    }) , run(new Runnable(){
				public void run() {
				    btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play"));
				    ScreensManager.inst().playGameScreen.changeColor();
				    ScreensManager.inst().playGameScreen.startGame();
				    moveOut();
				};
			    })));
			}else if(image==btnRate){
			    btnRate.addAction(sequence( run(new Runnable() {
				public void run() {
				    btnRate.moveBy(0f, 7f);
				}
			    }) , run(new Runnable(){
				public void run() {
				    btnRate.setTextureRegion(MyAtlas.playGameAtlas.findRegion("rate"));
				    ScreensManager.inst().game.controlHandler.showMoreApps();
				};
			    })));
			}else if(image==btnHighScore){
			    btnHighScore.addAction(sequence( run(new Runnable() {
				public void run() {
				    btnHighScore.moveBy(0f, 7f);
				}
			    }) , run(new Runnable(){
				public void run() {
				    btnHighScore.setTextureRegion(MyAtlas.playGameAtlas.findRegion("highscore"));
				    ScreensManager.inst().playGameScreen.setSetting();
				};
			    })));
			}
		}
		
	};
	
	
	public void moveOut(){
		this.addAction(Actions.sequence(Actions.moveTo(this.getX(), 0 ),Actions.moveTo(this.getX(), -BaseScreen.VIEWPORT_HEIGHT-200,0.5f),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		})));
	}
	
	
	public void moveIn(){
		this.addAction(Actions.sequence(Actions.moveTo(this.getX(), -BaseScreen.VIEWPORT_HEIGHT-100),Actions.moveTo(this.getX(), 0 ,0.5f),
				Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		})));
	}
	
	
	public void resetGame(){
		this.addAction(Actions.sequence(Actions.moveTo(0, 0 ,0.5f ),Actions.alpha(1f, 0.15f),Actions.moveTo(this.getX(), -BaseScreen.VIEWPORT_HEIGHT-200,0.5f),Actions.run(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ScreensManager.inst().playGameScreen.startGame();
			}
		})));
	}
}
