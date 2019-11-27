package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.List;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.ButtonBox;
import learn.nyx.com.gameanalyser.Screens.Buttons.PopupButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.PopupCloseButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.PopupConfirmButton;
import learn.nyx.com.gameanalyser.Structure.EventResult;
import learn.nyx.com.gameanalyser.Structure.Option;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * A popup which will allow the input of both players options and the result
 * Only one popup may exist at once, otherwise there might be problems and inconsistencies
 */
public class Popup {

    private Situation sit;
    private Option p1Choice; // id 0
    private Option p2Choice; // id 1
    private EventResult er; // id 2
    private String errorMsg = "";

    private Button cancel;
    private Button confirm;
    private SlidingPannel2 player1Choice;
    private SlidingPannel2 player2Choice;
    private ButtonBox result;


    public Popup(Situation sit) {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        this.sit = sit;
        PopupButton[] resultButton = createResultButtons();
        this.result = new ButtonBox(new Rect((int)(x*0.1), (int)(y*0.55), (int)(x*0.9), (int)(y*0.65)), resultButton);

        this.cancel = new PopupCloseButton(new Rect((int)(x*0.1), (int)(y*0.7), (int)(x*0.25), (int)(y*0.8)), this);
        this.confirm = new PopupConfirmButton(new Rect((int)(x*0.35), (int)(y*0.7), (int)(x*0.5), (int)(y*0.8)), this);


        this.player1Choice = new SlidingPannel2(new Rect((int)(x*0.1), (int)(y*0.1), (int)(x*0.45), (int)(y*0.5)), null);
        this.player2Choice = new SlidingPannel2(new Rect((int)(x*0.55), (int)(y*0.1), (int)(x*0.9), (int)(y*0.5)), null);

        List<Option> p1options = Constants.player1.getOptions(sit);
        List<Option> p2options = Constants.player2.getOptions(Constants.DATA.getOppositeSituation(sit));
        for (Option o : p1options) {
            player1Choice.add(new PopupButton(this, 0, o));
        }
        for (Option o : p2options) {
            player2Choice.add(new PopupButton(this, 1, o));
        }
    }



    public void draw(Canvas c) {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        c.drawRect((int)(x*0.05), (int)(y*0.05), (int)(x*0.95), (int)(y*0.90), paint);
        player1Choice.draw(c);
        player2Choice.draw(c);
        result.draw(c);
        cancel.draw(c);
        confirm.draw(c);
        paint.setColor(Color.BLACK);
        paint.setTextSize((float)(x*0.04));
        c.drawText("P1 option", (int)(x*0.1), (int)(y*0.08), paint);
        c.drawText("P2 option", (int)(x*0.55), (int)(y*0.08), paint);
        c.drawText("Result", (int)(x*0.1), (int)(y*0.54), paint);
        paint.setColor(Color.RED);
        c.drawText(errorMsg, (int)(x*0.1), (int)(y*0.85), paint);

    }

    public boolean onTouchEvent(MotionEvent e) {
        Button[] buttons = new Button[]{cancel, confirm};
        SlidingPannel2[] sliders = new SlidingPannel2[]{player1Choice, player2Choice};

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Button b : buttons) {
                    if (b != null) {
                    if (b.getRect().contains((int)e.getX(), (int)e.getY())) {
                        b.run();
                    }}
                }
                for (SlidingPannel2 s : sliders) {
                    if (s != null) {
                    if (s.getSpace().contains((int)e.getX(), (int)e.getY())) {
                        s.onTouchEvent(e);
                    }}
                }
                if (result.getSpace().contains((int)e.getX(), (int)e.getY())) {
                    result.onTouchEvent(e);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (SlidingPannel2 s : sliders) {
                    if (s.getSpace().contains((int)e.getX(), (int)e.getY())) {
                        s.onTouchEvent(e);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                for (SlidingPannel2 s : sliders) {
                    if (s.getSpace().contains((int)e.getX(), (int)e.getY())) {
                        s.onTouchEvent(e);
                    }
                }
                break;

        }

        return true;
    }

    public void setP1Choice(Option o) {
        this.p1Choice = o;
    }

    public void setP2Choice(Option o) {
        this.p2Choice = o;
    }

    public void setEr(EventResult er) {
        this.er = er;
    }

    public void confirm() {
        if ((p1Choice != null) && (p2Choice != null) && (er != null)) {
            Constants.POPUP_EVENT = this.createEvent();
            this.close();
        }
        else {
            this.errorMsg = "Please fill in the entire popup or cancel the input";
        }
    }

    public void close() {
        Constants.POPUP_OPEN = false;
    }

    public void open() {
        Constants.POPUP = this;
        Constants.POPUP_OPEN = true;

    }

    public Event createEvent() {
        return new Event(sit, p1Choice, p2Choice, er);
    }

    private PopupButton[] createResultButtons() {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        int dx = (int)(x*0.0186);
        int l = (int)(x*0.093);
        PopupButton[] resultButtons = new PopupButton[7];
        int i = 0;
        for (EventResult e : EventResult.values()) {
            resultButtons[i] = new PopupButton(new Rect((int)((i+1)*dx + i*l+0.1*x), (int)(y*0.58), (int)((i+1)*(dx+l)+0.1*x), (int)(y*0.62)), Event.getShortEventResultString(e), this, 2, e);
            i++;
        }
        return resultButtons;
    }

}
