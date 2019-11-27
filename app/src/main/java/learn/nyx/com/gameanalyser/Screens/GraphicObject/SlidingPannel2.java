package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.List;

import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Screen;

/**
 * This class is to be added to Screen
 * It contains an arbitrary number of Buttons, but is restricted to a certain space
 * It will also cycle between the buttons
 */
public class SlidingPannel2 {


    private List<Button> buttons;
    private Rect space;
    private int col = Color.rgb(150,150,150);
    private Float lastPt;
    private int dy;
    private int buttonHeight;
    private int indexStart;
    private int indexEnd;
    private Screen parent;


    public SlidingPannel2(Rect space, Screen parent) {
        this.parent=parent;
        this.space = space;
        this.buttons = new CycleList<Button>();
        this.dy = space.height()/16;
        this.buttonHeight = space.height()/8;
        this.indexStart = 0;
        this.indexEnd = 5;
    }

    public SlidingPannel2(Rect space, Screen parent, List<Button> b) {
        this(space, parent);
        for (Button bo : b) {
            this.add(bo);
        }
    }


    public SlidingPannel2(Rect space, Screen parent, Button[] b) {
        this(space, parent);
        for (Button bo : b) {
            this.add(bo);
        }
    }

    public Rect getSpace() {
        return space;
    }

    /**
     * Adds, resize and moves a button, so that it works with the panel
     * @param b the button to add
     */
    public void add(Button b) {
        if (this.buttons.size() == 0) {
            b.setRect(new Rect(space.left+space.width()/10, space.top+dy, space.right-space.width()/10, space.top + dy + buttonHeight));
        }
        else {
            int lastBot = this.buttons.get(this.buttons.size()-1).bottom;
            b.setRect(new Rect(space.left+space.width()/10, lastBot+dy, space.right-space.width()/10, lastBot + dy + buttonHeight));
        }
        this.buttons.add(b);
    }


    /**
     * Moves the buttons up
     * @param nb how much we want to move the buttons
     */
    private void up(float nb) {
        if (buttons.size() < 5) {
            return;
        }
        for (Button b : buttons) {
            b.up(nb);
        }
        if (buttons.get(indexStart).top < space.top) {
            indexStart ++;
        }
        if (space.bottom - buttons.get(indexEnd).bottom >= dy + buttonHeight) {
            int newTop = buttons.get(indexEnd).bottom + dy;
            int newBottom = newTop + buttonHeight;
            indexEnd ++;
            this.buttons.get(indexEnd).setRect(new Rect(space.left+space.width()/10, newTop, space.right-space.width()/10, newBottom));
        }


    }

    /**
     * Moves the buttons down
     * @param nb how much we want to move them
     */
    private void down(float nb) {
        if (buttons.size() < 5) {
            return;
        }
        for (Button b : buttons) {
            b.down(nb);
        }
        if (buttons.get(indexEnd).bottom > space.bottom) {
            indexEnd --;
        }
        if (buttons.get(indexStart).top - space.top >= dy + buttonHeight) {
            int newBottom = buttons.get(indexStart).top - dy;
            int newTop = newBottom - buttonHeight;
            indexStart --;
            this.buttons.get(indexStart).setRect(new Rect(space.left+space.width()/10, newTop, space.right-space.width()/10, newBottom));
        }
        //this.parent.debugText = Integer.toString(indexStart)+"     " + Integer.toString(indexEnd);
    }

    private void move(float dir) {
        if (dir > 0) {
            this.up(dir);
        }
        if (dir < 0) {
            this.down(-dir);
        }
    }

    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int size = e.getHistorySize();
                if (size > 0) {
                    float newPoint = (e.getHistoricalY(size-1));
                    float dir = 0;
                    if (lastPt == null) {
                        lastPt = newPoint;
                        return true;
                    }
                    dir = (float) lastPt-newPoint;
                    this.move(dir);
                    this.lastPt = newPoint;
                }
                break;
            case MotionEvent.ACTION_UP:
                this.lastPt = null;
                break;
            case MotionEvent.ACTION_DOWN:
                this.lastPt = null;
                for (Button b : buttons) {
                    b.setCol(Color.WHITE);
                }
                for (Button b : buttons) {
                    if (b.getRect().contains((int)e.getX(), (int)(e.getY()))) {
                        b.run();
                    }
                }
        }
        return true;
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(this.col);
        c.drawRect(space, paint);
        for (Button b : buttons) {
            if (space.contains(b.getRect())) {
                b.draw(c);
            }
        }
    }
}
