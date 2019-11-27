package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.GameScreen;

/**
 * A button to navigate from Game Screen to Display Screen
 */
public class Game2DisplayButton extends Button {

    private GameScreen g;

    public Game2DisplayButton(Rect r, GameScreen g) {
        super(r, "End");
        this.g = g;
    }

    @Override
    public void run() {
        if (g.getEvList().size() > 0) {
            Constants.MANAGER.UpdateEvLists(g.getEvList());
            Constants.MANAGER.setScreen(4);
        }
        g.debugText = ""+g.getEvList().size();
    }

}
