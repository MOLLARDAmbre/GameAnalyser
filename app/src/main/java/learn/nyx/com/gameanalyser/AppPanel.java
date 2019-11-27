package learn.nyx.com.gameanalyser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import learn.nyx.com.gameanalyser.Screens.ScreenManager;

/**
 * The application pannel, which will send everything to the screen manager and initialize what needs to be
 */
public class AppPanel  extends SurfaceView implements SurfaceHolder.Callback{

    private ScreenManager sm;
    private MainThread thread;

    public AppPanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        this.sm = new ScreenManager();
        Constants.MANAGER = sm;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch (Exception e) {

            }
            retry = false;
        }
    }


    public void update() {
        this.sm.getCurrentScreen().update();
    }

    public void draw(Canvas c) {
        super.draw(c);
        c.drawColor(Color.BLACK);
        sm.getCurrentScreen().draw(c);
        if (Constants.POPUP_OPEN) {
            Constants.POPUP.draw(c);
        }
    }


    public boolean onTouchEvent(MotionEvent e) {
        if (Constants.POPUP_OPEN) {
            Constants.POPUP.onTouchEvent(e);
        }
        else {
            sm.getCurrentScreen().onTouchEvent(e);
        }
        return true;
    }

}
