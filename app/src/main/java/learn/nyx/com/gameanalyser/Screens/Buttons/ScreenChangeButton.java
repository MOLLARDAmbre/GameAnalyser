package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;

/**
 * A generic button made to navigate between screens
 */
public class ScreenChangeButton extends Button {

    private int screenId;

    public ScreenChangeButton(Rect r, String l, int n) {
        super(r, l);
        screenId = n;
    }

    public void run() {
        Constants.MANAGER.setScreen(screenId);
    }

}
