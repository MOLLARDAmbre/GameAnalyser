package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.Screen;
import learn.nyx.com.gameanalyser.Structure.Situation;

/**
 * A button which changes the value of its parent
 */

public class ValueButton extends Button{

    private Screen parent;
    private Situation val;

    public ValueButton(Rect r, String l, Screen p, Situation val) {
        super(r, l);
        this.parent = p;
        this.val = val;
    }

    public void run(){
        this.parent.updateValue(this.val);
    }
}
