package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Canvas;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.ScreenChangeButton;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.EventShower;
import learn.nyx.com.gameanalyser.Structure.EventResult;
import learn.nyx.com.gameanalyser.Structure.Option;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * A testing screen
 */
public class TestScreen extends Screen {

    private Button back;
    private EventShower evs;



    public TestScreen() {

        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;

        Situation test = new Situation("Test");
        Option o0 = new Option("0");
        Option o1 = new Option("1");
        Option o2 = new Option("2");
        Option o3 = new Option("3");
        Event e0 = new Event(test, o0, o0, EventResult.Kill);
        Event e1 = new Event(test, o1, o1, EventResult.WinSlight);
        Event e2 = new Event(test, o0, o0, EventResult.WinHard);
        Event e3 = new Event(test, o0, o0, EventResult.WinSlight);
        Event e4 = new Event(test, o0, o0, EventResult.Die);
        Event e5 = new Event(test, o2, o2, EventResult.LoseSlight);
        Event e6 = new Event(test, o2, o2, EventResult.WinHard);
        Event e7 = new Event(test, o3, o3, EventResult.WinSlight);
        Event e8 = new Event(test, o1, o1, EventResult.LoseHard);
        Event e9 = new Event(test, o1, o1, EventResult.Die);
        Event e10 = new Event(test, o3, o3, EventResult.LoseHard);
        Event e11 = new Event(test, o0, o0, EventResult.LoseHard);
        Event e12 = new Event(test, o2, o2, EventResult.Die);
        this.evs = new EventShower(new Rect((int)(x*0.2), (int)(y*0.4), (int)(x*0.8), (int)(y*0.6)), this, new Event[]{e0,e1,e2,e3,e4,e5,e6,e7});//,e8,e9,e10,e11,e12});
        this.es = new EventShower[]{evs};

        this.buttons = new Button[]{new ScreenChangeButton(new Rect(0,0, 100,100), "back", 0)};

    }

    public void draw(Canvas c) {
        this.debugText = this.es[0].getOptionInfos();
        super.draw(c);
    }


}
