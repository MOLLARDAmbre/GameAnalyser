package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.ButtonBox;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.EventShower;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.SlidingPannel2;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * The generic definition of what a screen is
 */
public abstract class Screen {

    protected Button[] buttons = new Button[]{};
    protected ButtonBox[] bboxs = new ButtonBox[]{};
    protected SlidingPannel2[] sliders = new SlidingPannel2[]{};
    protected Event event = null;
    protected EventShower[] es = new EventShower[]{};
    public String debugText = "";


    /**
     * Allows the screen to change its state if some calculation is to be made every frame
     */
    public void update() {};

    /**
     * Allows the use of buttons that will hide/show graphical elements
     */
    public void display() {};

    public void updateValue(Situation s) {};

    /**
     * Draws the screen
     * @param c the canvas to draw on
     */
    public void draw(Canvas c) {
        if (buttons != null) {
            for (Button b : buttons) {
                b.draw(c);
            }
        }
        if (sliders != null) {
            for (SlidingPannel2 s : sliders) {
                s.draw(c);
            }
        }
        if (bboxs != null) {
            for (ButtonBox b : bboxs) {
                b.draw(c);
            }
        }
        if (event != null) {
            event.draw(c);
        }
        if(es != null) {
            for (EventShower e : es) {
                e.draw(c);
            }
        }
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        c.drawText(debugText, (int) Constants.SCREEN_WIDTH/2, (int)Constants.SCREEN_HEIGHT-100, paint);

    }


    /**
     * Send the touch event to other graphic objects
     * @param e the motion event
     * @return true
     */
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Button b : buttons) {
                    if (b.getRect().contains((int)e.getX(), (int)e.getY())) {
                        b.run();
                    }
                }
                if (sliders != null) {
                    for (SlidingPannel2 s : sliders) {
                        if (s.getSpace().contains((int) e.getX(), (int) e.getY())) {
                            s.onTouchEvent(e);
                        }
                    }
                }
                for (ButtonBox b : bboxs) {
                    if (b.getSpace().contains((int)e.getX(), (int)e.getY())) {
                        b.onTouchEvent(e);
                    }
                }
                if (es != null) {
                    for (EventShower ev : es) {
                        if (ev.getSpace().contains((int)e.getX(),(int) e.getY())) {
                            ev.onTouchEvent(e);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (sliders != null) {
                    for (SlidingPannel2 s : sliders) {
                        if (s.getSpace().contains((int) e.getX(), (int) e.getY())) {
                            s.onTouchEvent(e);
                        }
                    }
                }
                if (es != null) {
                    for (EventShower ev : es) {
                        if (ev.getSpace().contains((int)e.getX(), (int)e.getY())) {
                            ev.onTouchEvent(e);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (sliders != null) {
                    for (SlidingPannel2 s : sliders) {
                        if (s.getSpace().contains((int) e.getX(), (int) e.getY())) {
                            s.onTouchEvent(e);
                        }
                    }
                }
                if (es != null) {
                    for (EventShower ev : es) {
                        if (ev.getSpace().contains((int)e.getX(), (int)e.getY())) {
                            ev.onTouchEvent(e);
                        }
                    }
                }
                break;

        }

        return true;
    }
}
