package vn.pp.freakingadult.group;

import vn.pp.PPImage;
import vn.pp.freakingadult.BlockGame;
import vn.pp.freakingadult.assets.MyAtlas;
import vn.pp.freakingadult.assets.MySound;
import vn.pp.freakingadult.group.MyClickListener.ItfScreenShot;
import vn.pp.freakingadult.screens.BaseScreen;
import vn.pp.freakingadult.screens.ScreensManager;
import vn.pp.freakingadult.utils.MyPrefs;
import vn.pp.freakingadult.utils.ScreenshotSaver;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ResultGroup extends Group implements ItfScreenShot{
	public PPImage imageBg;
	public PPImage popup;
	public PPImage btnPlay,btnMenu,btnFb;
	public Label txtScore,txtBestScore;
	
	Stage stage;
	public ResultGroup(Stage stage){
		super();
		this.setWidth(BaseScreen.VIEWPORT_WIDTH);
		this.setHeight(BaseScreen.VIEWPORT_HEIGHT);
		this.stage = stage;
		init();
		this.setPosition(0, BaseScreen.VIEWPORT_HEIGHT+100);
	}
	
	public void init(){
		imageBg=new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(BaseScreen.VIEWPORT_WIDTH+200);
		imageBg.setHeight(BaseScreen.VIEWPORT_HEIGHT+200);
		imageBg.setPosition(-100, -100);
		this.addActor(imageBg);
		imageBg.setColor(new Color(0f/255, 0f/255, 0f/255, 0f/255));
		
		popup=new PPImage(MyAtlas.playGameAtlas.findRegion("gameover"));
		popup.setX(BaseScreen.VIEWPORT_WIDTH/2-popup.getWidth()/2);
		popup.setY(350);
		this.addActor(popup);
		
		btnPlay=new PPImage(MyAtlas.playGameAtlas.findRegion("play2"));
		btnPlay.setX(BaseScreen.VIEWPORT_WIDTH/2-10-btnPlay.getWidth());
		btnPlay.setY(230);
		this.addActor(btnPlay);
		MyClickListener clickPlay= new MyClickListener(new Runnable() {
			public void run() {
				btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play2"));
				moveOut();
				ScreensManager.inst().playGameScreen.resetGame();
				if(MyPrefs.getSound()){
					MySound.restart.play();
				}
			}
		});
		btnPlay.addListener(clickPlay);
//		btnPlay.addListener(btnClick);
		
		btnMenu=new PPImage(MyAtlas.playGameAtlas.findRegion("menu"));
		btnMenu.setX(BaseScreen.VIEWPORT_WIDTH/2+10);
		btnMenu.setY(230);
		this.addActor(btnMenu);
		MyClickListener clickMenu= new MyClickListener(new Runnable() {
		    public void run() {
			btnMenu.setTextureRegion(MyAtlas.playGameAtlas.findRegion("menu"));
			moveOut();
			ScreensManager.inst().playGameScreen.menuGame();
		    }
		});
		btnMenu.addListener(clickMenu);
		
		LabelStyle lbStyle2=new LabelStyle(BlockGame.font50, Color.WHITE);
		txtScore=new Label("0", lbStyle2);
		txtScore.setFontScale(0.8f);
		txtScore.setAlignment(Align.left);
		txtScore.setColor(170f/255, 86f/255, 41f/255, 1f);
		txtScore.setPosition(320, 510);
		this.addActor(txtScore);
		
		txtBestScore=new Label("0", lbStyle2);
		txtBestScore.setAlignment(Align.left);
		txtBestScore.setFontScale(0.8f);
		txtBestScore.setColor(170f/255, 86f/255, 41f/255, 1f);
		txtBestScore.setPosition(320, 460);
		this.addActor(txtBestScore);
		
		btnFb=new PPImage(MyAtlas.playGameAtlas.findRegion("fshare"));
		btnFb.setX(BaseScreen.VIEWPORT_WIDTH/2-btnFb.getWidth()/2);
		btnFb.setY(370);
		this.addActor(btnFb);
		MyClickListener clickFace= new MyClickListener(new Runnable() {
		    public void run() {
			
		    }
		});
		clickFace.setItfScreenShot(this);
		btnFb.addListener(clickFace);
//		btnFb.addListener(btnClick);
	}
	
	
	public void setScore(int score){
		txtScore.setText(""+score);
		txtBestScore.setText(""+MyPrefs.getBestScore());
		if(score>MyPrefs.getScore()){
		    ScreensManager.inst().game.controlGGS.submitScoreGPGS(score);
		}
		//test
//		ScreensManager.inst().game.controlGGS.submitScoreGPGS(210);
	}

	ClickListener btnClick=new ClickListener(){

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			// TODO Auto-generated method stub
			PPImage image=(PPImage)event.getTarget();
			if(image==btnPlay){
//				btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play_select"));
			}else if(image==btnMenu){
//				btnMenu.setTextureRegion(MyAtlas.playGameAtlas.findRegion("menu_select"));
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			// TODO Auto-generated method stub
			super.touchUp(event, x, y, pointer, button);
			PPImage image=(PPImage)event.getTarget();
			if(image==btnPlay){
				btnPlay.setTextureRegion(MyAtlas.playGameAtlas.findRegion("play2"));
				moveOut();
				ScreensManager.inst().playGameScreen.resetGame();
				if(MyPrefs.getSound()){
					MySound.restart.play();
				}
			}else if(image==btnMenu){
				btnMenu.setTextureRegion(MyAtlas.playGameAtlas.findRegion("menu"));
				moveOut();
				ScreensManager.inst().playGameScreen.menuGame();
			}else if(image==btnFb){
				String path = "";
				try {
//					ScreensManager.inst().game.controlHandler.showLoading();
//					path=ScreenshotSaver.saveScreenshotPro(MyPrefs.getBestScore());
//					
//					ScreensManager.inst().game.controlHandler.shareFbDetail(MyPrefs.getBestScore());
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		ScreensManager.inst().game.controlHandler.showAd(true);
	}
	
	public void moveOut(){
		imageBg.getColor().a=0f;
		this.addAction(Actions.sequence(Actions.moveTo( 0 , 0 ),Actions.moveTo(this.getX(), BaseScreen.VIEWPORT_HEIGHT+100 ),Actions.run(new Runnable() {
			
			@Override
			public void run() {
			    ScreensManager.inst().game.controlHandler.showAd(false);
			}
		})));
	}

	@Override
	public void callScreenShot() {
	    String path = "";
	    float widthscreen = stage.getCamera().viewportWidth;
	    float heightScreen = stage.getCamera().viewportHeight;
	    System.out.println("---w:"+widthscreen+"--h:"+heightScreen);
	    try {
		ScreensManager.inst().game.controlHandler.showLoading();
		path=ScreenshotSaver.saveScreenshotPro();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    System.out.println("path: "+path);
	    if(path!=null && !path.equals("")){
		ScreensManager.inst().game.controlHandler.shareFbDetail(0);
	    } else {
		ScreensManager.inst().game.controlHandler.shareFb();
	    }
	}
}
