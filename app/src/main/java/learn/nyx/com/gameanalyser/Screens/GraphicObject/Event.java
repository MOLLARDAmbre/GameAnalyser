package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Structure.EventResult;
import learn.nyx.com.gameanalyser.Structure.Option;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * This class will register and display an event. It contains a situation, and both players options alongside the result.
 */
public class Event {

    private Situation sit;
    private Option p1Choice;
    private Option p2Choice;
    private EventResult res;
    private Rect r = new Rect(0,0,0,0);

    public Event(Situation sit, Option o1, Option o2, EventResult er) {
        this.sit = sit;
        this.res = er;
        this.p1Choice = o1;
        this.p2Choice = o2;
    }

    public static String getEventResultString(EventResult er) {
        switch (er) {
            case Die:
                return "You died";
            case LoseHard:
                return "You lost a lot";
            case LoseSlight:
                return "You slightly lost";
            case Even:
                return "The result was even";
            case WinSlight:
                return "You won a bit";
            case WinHard:
                return "You won a lot";
            case Kill:
                return "You killed";
        }
        return "";
    }

    public static String getShortEventResultString(EventResult er) {
        switch (er) {
            case Die:
                return "Died";
            case LoseHard:
                return "Hard Loss";
            case LoseSlight:
                return "Slight Loss";
            case Even:
                return "Even";
            case WinSlight:
                return "Slight Win";
            case WinHard:
                return "Hard Win";
            case Kill:
                return "Killed";
        }
        return "";
    }

    public int getColor(){
        return getColor(this.res);
    }

    public Option getP1Option() {
        return p1Choice;
    }
    public Option getP2Option() {
        return p2Choice;
    }

    public Situation getSituation() {
        return sit;
    }

    public static int getColor(EventResult er) {
        switch (er) {
            case Die:
                return Color.rgb(153, 0, 0);
            case LoseHard:
                return Color.rgb(204, 102, 0);
            case LoseSlight:
                return Color.rgb(204,204,0);
            case Even:
                return Color.rgb(39,205,0);
            case WinSlight:
                return Color.rgb(0, 200, 103);
            case WinHard:
                return Color.rgb(77,77,255);
            case Kill :
                return Color.WHITE;
        }
        return 0;
    }

    public void setRect(Rect re) {
        this.r = re;
    }

    public Rect getRect() {
        return this.r;
    }

    public void left(float nb) {
        float d = nb;
        this.r.left -= nb;
        this.r.right -= nb;
    }

    public void right(float nb) {
        float d = nb;
        this.r.left += nb;
        this.r.right += nb;
    }

    public void draw(Canvas c) {
        int x = (int) Constants.SCREEN_WIDTH;
        int y = (int) Constants.SCREEN_HEIGHT;
        Paint paint = new Paint();
        paint.setColor(Color.rgb(150,250,250));
        c.drawRect((int)(x*0.2), (int)(y*0.2), (int)(x*0.8), (int)(y*0.8), paint);
        paint.setColor(getColor(this.res));
        paint.setTextSize(x/20);
        c.drawText(sit.toString(), (int)(x*0.35), (int)(y*0.35), paint);
        c.drawText(p1Choice.toString(), (int)(x*0.35), (int)(y*0.45), paint);
        c.drawText(p2Choice.toString(), (int)(x*0.35), (int)(y*0.55), paint);
        c.drawText(getEventResultString(res), (int)(x*0.35), (int)(y*0.65), paint);
        paint.setTextSize(x/30);
        c.drawText("Situation", (int)(x*0.2), (int)(y*0.3), paint);
        c.drawText("Your option", (int)(x*0.2), (int)(y*0.4), paint);
        c.drawText("Opponent option", (int)(x*0.2), (int)(y*0.5), paint);
        c.drawText("Result", (int)(x*0.2), (int)(y*0.6), paint);

    }

}
