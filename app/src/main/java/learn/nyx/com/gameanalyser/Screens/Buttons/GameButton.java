package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.GraphicObject.Popup;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * A button that opens a popup to enter an option. Meant to be used in GameScreen
 */
public class GameButton extends ConfirmButton {

    private Situation sit;

    public GameButton(String label, Situation sit) {
        super(new Rect(0,0,0,0), label);
        this.sit = sit;
    }

    public GameButton(Rect r, String l, Situation sit) {
        super(r, l);
        this.sit = sit;
    }

    public GameButton(Situation sit) {
        super(new Rect(0,0,0,0), sit.toString());
        this.sit = sit;
    }
    public void click() {
        Popup pop = new Popup(this.sit);
        pop.open();
    }
}
