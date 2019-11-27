package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Arrow;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.Game2DisplayButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.GameButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.ScreenChangeButton;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.SlidingPannel2;
import learn.nyx.com.gameanalyser.Structure.Character;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * The screen which allows to pick a situation and generate a popup to enter the options picked
 */
public class GameScreen extends Screen {

    private Situation lands;
    private Situation catchesLanding;
    private Situation ledgeTraps;
    private Situation isLedgetrapped;
    private Button back;
    private Button end;
    private Arrow leftArrow;
    private Arrow rightArrow;
    private SlidingPannel2 gb;
    private List<Event> evList;
    private int cursor;
    private SlidingPannel2[] hidden;

    public GameScreen() {

        this.cursor = 0;
        this.evList = new ArrayList<Event>();
        ArrayList<Situation> sits = Constants.DATA.getSituations();

        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;

        this.leftArrow = new Arrow(0, new Rect(0, (int)(y*0.4), (int)(x*0.1), (int)(y*0.6)), this);
        this.rightArrow = new Arrow(1, new Rect((int)(x*0.9), (int)(y*0.4), (int)(x*1), (int)(y*0.6)), this);
        this.end = new Game2DisplayButton(new Rect((int)(x*0.8),(int)(y*0.85),(int)(x*0.95), (int)(y*0.90)), this);

        this.gb = new SlidingPannel2(new Rect((int)(x*0.2), (int)(y*0.2), (int)(x*0.8), (int)(y*0.8)), this);
        for (Situation s : sits) {
            this.gb.add(new GameButton(s));
        }
        this.back = new ScreenChangeButton(new Rect(0, 0, Constants.SCREEN_WIDTH/5, Constants.SCREEN_HEIGHT/20), "Back",0);
        this.buttons = new Button[]{back, leftArrow, rightArrow, end};
        this.sliders = new SlidingPannel2[]{this.gb};
        this.hidden = new SlidingPannel2[]{this.gb};


    }

    public void left() {
        if (cursor > 0) {
            cursor --;
        }
    }

    public void right() {
        if (cursor < this.evList.size()) {
            cursor ++;
        }

    }


    public void update() {
        if (Constants.POPUP_EVENT != null) {
            this.evList.add(Constants.POPUP_EVENT);
            Constants.POPUP_EVENT = null;
        }

        if (this.cursor == this.evList.size()) {
            this.sliders = this.hidden;
            this.event = null;
        }
        else {
            //this.sliders = new SlidingPannel2[]{};
            this.event = this.evList.get(cursor);
        }
    }

    public void updateEvList(List<Event> l) {
        this.evList = l;
    }

    public List<Event> getEvList() {
        return evList;
    }


}
