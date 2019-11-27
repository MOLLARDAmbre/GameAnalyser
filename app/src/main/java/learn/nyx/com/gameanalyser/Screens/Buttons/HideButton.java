package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.Screen;

/**
 * A button made for DisplayScreen. Allows to display new objects when clicked
 */
public class HideButton extends Button {

    private Screen parent;

    public HideButton(Rect r, String l, Screen p) {
        super(r, l);
        this.parent = p;
    }

    public void run() {

        this.parent.display();

    }

}
