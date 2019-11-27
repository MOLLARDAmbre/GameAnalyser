package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import java.util.List;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.DisplayScreen;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;

/**
 * This button allows moving between Display screen and Game screen
 */
public class Display2GameButton extends Button {

    private DisplayScreen parent;

    public Display2GameButton(Rect r, DisplayScreen p) {
        super(r, "Back");
        this.parent = p;
    }


    @Override
    public void run() {
        Constants.MANAGER.UpdateEvLists(parent.getEvList());
        Constants.MANAGER.setScreen(2);
    }

}
