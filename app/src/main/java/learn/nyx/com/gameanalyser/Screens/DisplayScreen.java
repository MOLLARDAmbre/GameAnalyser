package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.Display2GameButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.HideButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.ValueButton;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.EventShower;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.SlidingPannel2;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * A screen made to display the results of the game
 * It will feature a button which will display other buttons to select the situation
 * Then it displays an event shower with the (soon right, right now all) events
 */
public class DisplayScreen extends Screen {

    private List<Event> evList;
    private List<Button> situationButtons;
    private HideButton hideButton;
    private Situation currentSit;
    private EventShower shower;
    private Button back;

    public DisplayScreen(List<Event> l) {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        this.evList = l;
        this.back = new Display2GameButton(new Rect(0, 0, x/5, y/20), this);
        this.hideButton = new HideButton(new Rect((int)(x*0.75), (int)(y*0), (int)(x*1), (int)(y*0.05)), "Situations", this);
        // TODO Make the situation buttons
        this.shower = new EventShower(new Rect((int)(x*0.2), (int)(y*0.4), (int)(x*0.8), (int)(y*0.6)), this, evList);
        this.buttons = new Button[]{back, hideButton};

    }


    public void display() {
        HashSet<Situation> sits = new HashSet<Situation>();
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        Rect sp = new Rect((int)(x*0.75), (int)(y*0.05), (int)(x), (int)(y*0.35));
        for (Event e : evList) {
            sits.add(e.getSituation());
        }
        SlidingPannel2 situationChoose = new SlidingPannel2(sp, this);
        for (Situation s : sits) {
            situationChoose.add(new ValueButton(new Rect(0,0,0,0), s.toString(), this, s));
        }
        this.sliders = new SlidingPannel2[]{situationChoose};
    }

    public void updateValue(Situation s) {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        this.currentSit = s;
        this.hide();
        EventShower shower = new EventShower(new Rect((int)(x*0.2), (int)(y*0.4), (int)(x*0.8), (int)(y*0.6)), this, this.getSitEvList());
        this.es = new EventShower[]{shower};
    }

    public void hide() {
        this.sliders = new SlidingPannel2[]{};
    }

    public void updateEvList(List<Event> l) {
        this.evList = l;
        int x = (int)Constants.SCREEN_WIDTH;
        int y = (int)Constants.SCREEN_HEIGHT;
        this.currentSit = l.get(0).getSituation();
        this.shower = new EventShower(new Rect((int)(x*0.2), (int)(y*0.4), (int)(x*0.8), (int)(y*0.6)), this, this.getSitEvList());
        this.es = new EventShower[]{shower};
    }


    public List<Event> getEvList() {
        return evList;
    }

    public List<Event> getSitEvList() {
        ArrayList<Event> l = new ArrayList<>();
        for (Event e : this.evList) {
            if (e.getSituation() == this.currentSit) {
                l.add(e);
            }
        }
        return l;

    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        c.drawText(this.currentSit.toString(), (int)(Constants.SCREEN_WIDTH*0.3), Constants.SCREEN_HEIGHT / 5, paint);
        super.draw(c);
    }

}
