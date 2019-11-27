package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.GraphicObject.Popup;
import learn.nyx.com.gameanalyser.Structure.EventResult;
import learn.nyx.com.gameanalyser.Structure.Option;

/**
 * A button made specifically for popups, to update their values
 */
public class PopupButton extends Button {

    private int col;
    private Popup pop;
    private int attributeId;
    private Option o;
    private EventResult er;

    public PopupButton(Rect r, String label, Popup pop, int attributeId) {
        super(r, label);
        this.col = Color.WHITE;
        this.pop = pop;
        this.attributeId = attributeId;
    }

    public PopupButton(Rect r, String l, Popup pop, int atid, Option o) {
        this(r, l, pop, atid);
        this.o = o;
    }

    public PopupButton(Popup pop, int atid, Option o) {
        this(new Rect(0,0,0,0), o.toString(), pop, atid, o);
    }

    public PopupButton(Rect r, String l, Popup pop, int atid, EventResult er) {
        this(r, l, pop, atid);
        this.er = er;
    }

    public void run() {
        this.col = Color.YELLOW;
        switch (this.attributeId) {
            case 0:
                this.pop.setP1Choice(this.o);
            case 1:
                this.pop.setP2Choice(this.o);
            case 2:
                this.pop.setEr(this.er);
        }
        return;
    }

    public void setCol(int i) {
        this.col = i;
    }


    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(this.col);
        super.draw(c, paint);
    }
}
