package vn.pp.freakingadult.group;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import vn.pp.PPImage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MyClickListener extends ClickListener {

    int moveNum = 7;
    Runnable runnable;
    ItfScreenShot itfScreenShot;
    
    public MyClickListener(Runnable runnable) {
	this.runnable = runnable;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	PPImage image = (PPImage) event.getTarget();
	image.moveBy(0, -moveNum);

	return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	super.touchUp(event, x, y, pointer, button);
	final PPImage image = (PPImage) event.getTarget();
	image.addAction(sequence(run(new Runnable() {
	    public void run() {
		image.moveBy(0f, 7f);
	    }
	}), run(runnable)));
	if (this.itfScreenShot != null) {
	    this.itfScreenShot.callScreenShot();
	}
    }

    public void setItfScreenShot(ItfScreenShot itfScreenShot) {
	this.itfScreenShot = itfScreenShot;
    }

    interface ItfScreenShot {
	public void callScreenShot();
    }
}
