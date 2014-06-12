package vn.pp.freakingadult.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BaseScreen implements Screen, InputProcessor {

//	public static int VIEWPORT_WIDTH = 800, VIEWPORT_HEIGHT = 1280;
	public static int VIEWPORT_WIDTH = 480, VIEWPORT_HEIGHT = 800;
	public Stage stage;
	public boolean isChangeScreen = false;
	boolean isBackButtonActive;
	public final InputMultiplexer inputMultiplexer;
	
	Camera camera;
	
	public BaseScreen() {
	    System.out.println("-----------base screen init-------------");
//	    initViewPort();
	    stage = new Stage(new StretchViewport(  VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
//		stage = new Stage(new ExtendViewport(  VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
		// myPrefs=new MyPrefs();
		this.inputMultiplexer = new InputMultiplexer(this);
		inputMultiplexer.addProcessor(stage);
	}

    int wStand = 1280;
    int hStand = 800;

  
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
//		Gdx.gl20.glClearColor(196f/255, 63f/255, 63f/255, 1f);
	    	Gdx.gl20.glClearColor(0f, 0f, 0f, 0f);
//	    	Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		
		stage.draw();
	}

	@Override
	public void resize(int w, int h) {
	    
//		stage.setViewport(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
//		stage.getViewport().update(w, h, true);
////		System.out.println("---width: "+w+"---height:"+h);
//		stage.getCamera().position.x=VIEWPORT_WIDTH/2;
//		stage.getCamera().position.y=VIEWPORT_HEIGHT/2;
//		stage.getCamera().update();
		
//		System.out.println("--stage.getCamera().viewportWidth: "+stage.getCamera().viewportWidth
//			+"\n--stage.getCamera().viewportHeight: "+stage.getCamera().viewportHeight
//			);
//		System.out.println("---stage.getHeight(): "+stage.getHeight());

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
		Gdx.app.log("BaseScreen", "dispose");
	}


	public void keyBackPressed() {

		Gdx.app.log("BaseScreen", "keyBackPressed");
	}
	
	
	public void setActiveBackButton(boolean _isActiveBackButton){
		this.isBackButtonActive=_isActiveBackButton;
		Gdx.input.setCatchBackKey(isBackButtonActive);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.BACK) {
			if (isBackButtonActive) {
				keyBackPressed();
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
