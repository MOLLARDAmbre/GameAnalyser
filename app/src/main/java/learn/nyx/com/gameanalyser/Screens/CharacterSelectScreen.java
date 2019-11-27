package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Rect;

import java.util.ArrayList;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.CharSelectButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.CharacterSetButton;
import learn.nyx.com.gameanalyser.Screens.Buttons.ScreenChangeButton;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.SlidingPannel2;

/**
 * A screen which allows to choose the characters
 */
public class CharacterSelectScreen extends Screen {

    private ArrayList<Button> p1;
    private ArrayList<Button> p2;
    private SlidingPannel2 p1s;
    private SlidingPannel2 p2s;

    private Button back;

    public CharacterSelectScreen() {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        this.p1 = new ArrayList<Button>();
        this.p2 = new ArrayList<Button>();
        int i = 0;
        for (String c : Constants.DATA.getCharacters()) {
            p1.add(new CharSelectButton(1, c));
            p2.add(new CharSelectButton(2, c));
            i ++;
        }
        this.p1s = new SlidingPannel2(new Rect((int)(x*0.1), (int)(y*0.2), (int)(x*0.45), (int)(y*0.9)), this, p1);
        this.p2s = new SlidingPannel2(new Rect((int)(x*0.55), (int)(y*0.2), (int)(x*0.9), (int)(y*0.9)), this, p2);

        this.sliders = new SlidingPannel2[]{this.p1s, this.p2s};

        this.back = new CharacterSetButton(new Rect(0, 0, Constants.SCREEN_WIDTH/5, Constants.SCREEN_HEIGHT/20), "Back",0);
        this.buttons = new Button[]{back};

    }

}
