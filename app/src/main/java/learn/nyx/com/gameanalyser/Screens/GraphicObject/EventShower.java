package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import learn.nyx.com.gameanalyser.Screens.Screen;
import learn.nyx.com.gameanalyser.Structure.Option;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * A graphic object which will display the events it is given. The events will have a different color based on
 * the 1st player option, and will show the result of the event as well.
 * It can be swiped from left to right to display events if there are more than 10 of them
 */
public class EventShower {

    private List<Event> evList;
    private Rect space;
    private int col = Color.rgb(150,150,150);
    private Float lastPt;
    private int evLength;
    private int indexStart;
    private int indexEnd;
    private Screen parent;
    private ColorWheel cw;



    public EventShower(Rect space, Screen parent) {
        this.parent=parent;
        this.space = space;
        this.evList = new CycleList<Event>();
        this.evLength = space.width()/10;
        this.indexStart = 0;
        this.indexEnd = 9;
        this.cw = new ColorWheel(new ArrayList<Option>());
    }

    public EventShower(Rect space, Screen parent, List<Event> b) {
        this(space, parent);
        for (Event bo : b) {
            this.add(bo);
        }
    }


    public EventShower(Rect space, Screen parent, Event[] b) {
        this(space, parent);
        for (Event bo : b) {
            this.add(bo);
        }
    }

    public Rect getSpace() {
        return space;
    }

    /**
     * Adds an event, while making sure it is properly sized and positionned
     * @param b an event we want to add
     */
    public void add(Event b) {
        if (this.evList.size() == 0) {
            b.setRect(new Rect(space.left+2, space.top+2, space.left+evLength-2, space.bottom-2));
        }
        else {
            int lastRight = this.evList.get(this.evList.size()-1).getRect().right+3;
            b.setRect(new Rect(lastRight+2, space.top+2, lastRight+evLength-2, space.bottom-2));

        }
        this.evList.add(b);
        this.cw.add(b.getP1Option());
    }


    /**
     * Swipe the event shower to the left
     * @param nb how much we want to swipe
     */
    private void left(float nb) {

        if (evList.get(evList.size()-1).getRect().right <= space.right) {
            /*float n = space.right-evList.get(evList.size()-1).getRect().right;
            for (Event e : evList) {
                e.right(n);
            }*/ // See right
            return;
        }

        if (evList.size() < 10) {
            return;
        }
        for (Event b : evList) {
            b.left(nb);
        }
        if (evList.get(indexStart).getRect().right < space.left) {
            indexStart ++;
        }
        if (evList.get(indexEnd).getRect().left >= space.right) {
            indexEnd ++;
        }


    }

    /**
     * Swipe the event shower to the right
     * @param nb how much we want to swipe
     */
    private void right(float nb) {


        if (evList.get(0).getRect().left >= space.left) {
            /*float n = space.left-evList.get(0).getRect().left;
            for (Event e : evList) {
                e.left(n);
            }*/ // This piece of code can improve how it works, but is not working yet // TODO
            return;
        }
        if (evList.size() < 10) {
            return;
        }
        for (Event b : evList) {
            b.right(nb);
        }
        if (evList.get(indexEnd).getRect().left > space.right) {
            indexEnd --;
        }
        if (evList.get(indexStart).getRect().right >= space.left) {
            indexStart --;
        }
    }

    private void move(float dir) {
        if (dir > 0) {
            this.left(dir);
        }
        if (dir < 0) {
            this.right(-dir);
        }
    }

    /**
     * This function is meant to allow the event shower to display more info on the options displayed
     * For now it is not used, but might be later
     * @return A string describing the options chosen
     */
    public String getOptionInfos() {
        String[] opts1 = new String[11];
        String[] opts2 = new String[11];
        int resScore = 0;
        int i = 0;
        for (Event e : evList) {
            Rect r = e.getRect();
            if (space.contains(r.left, r.top) || (space.contains(r.right, r.top))) {
                opts1[i] = e.getP1Option().toString();
                opts2[i] = e.getP2Option().toString();
                i+=1;
            }
        }
        i=0;
        int[] scores = new int[11];
        int score;
        for (String s : opts1) {
            score = 0;
            for (String si : opts1) {
                if (s == si) {
                    score += 1;
                }
            }
            scores[i] = score;
            i++;
        }

        String s = "";
        for (int j : scores) {
            s += " " + Integer.toString(j) + " ";
        }



        return s;

    }

    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int size = e.getHistorySize();
                if (size > 0) {
                    float newPoint = (e.getHistoricalX(size-1));
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
                break;
        }
        return true;
    }

    /**
     * Adds several events
     * @param ev the event array to add
     */
    public void add(Event[] ev) {
        for (Event e : ev) {
            this.add(e);
        }
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(col);
        c.drawRect(space, paint);
        for (Event e : evList) {
            Rect r = e.getRect();
            if (space.contains(r.left, r.top)) {
                paint.setColor(cw.getColor(e.getP1Option()));
                c.drawRect(new Rect(r.left, r.top, min(r.right, space.right), r.bottom), paint);
                paint.setColor(e.getColor());
                c.drawRect(new Rect(r.left, r.bottom - (r.bottom-r.top)/5, min(r.right, space.right), r.bottom), paint);
            }
            if (space.contains(r.right, r.top)) {
                paint.setColor(cw.getColor(e.getP1Option()));
                c.drawRect(new Rect(max(r.left, space.left), r.top, r.right, r.bottom), paint);
                paint.setColor(e.getColor());
                c.drawRect(new Rect(r.left, r.bottom - (r.bottom-r.top)/5, min(r.right, space.right), r.bottom), paint);
            }
        }
        Rect r = this.space;
        this.drawOptionNames(c);
        //this.parent.debugText = Integer.toString(r.left) + "   " + Integer.toString(r.right) + "   "+ Integer.toString(r.top);
        return;

        // TODO have result shown at the bottom of it if we find a good way to display them
    }

    public ColorWheel getColorWheel() {
        return this.cw;
    }

    /**
     * This draws below the event shower the name of all options currently displayed by the event shower with their color
     */
    public void drawOptionNames(Canvas c) {
        int i = 0;
        HashSet<Option> opts1 = new HashSet<Option>();
        for (Event e : evList) {
            Rect r = e.getRect();
            if (space.contains(r.left, r.top) || (space.contains(r.right, r.top))) {
                opts1.add(e.getP1Option());
            }
        }
        i = 0;
        Paint paint = new Paint();
        paint.setTextSize(30);
        for (Option o : opts1) {
            i ++;
            paint.setColor(cw.getColor(o));
            c.drawText(o.toString(), space.left, space.bottom + 40 * i, paint);
        }
    }


}
