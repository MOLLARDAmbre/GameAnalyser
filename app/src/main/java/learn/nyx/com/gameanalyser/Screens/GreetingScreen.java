package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.ScreenChangeButton;


/**
 * The main screen of the app.
 * Only features buttons to navigate to a more relevant screen
 */
public class GreetingScreen extends Screen {

    private Button charSelect;
    private Button newGame;
    private Button constState;

    public GreetingScreen() {
        int x = Constants.SCREEN_WIDTH;
        int y = Constants.SCREEN_HEIGHT;
        this.charSelect = new ScreenChangeButton(new Rect(0, 0, x/5, y/30), "Character select", 1);
        this.newGame = new ScreenChangeButton(new Rect(x/5, (int)(y*0.3), (int)(x *0.8), (int)(y*0.4)), "New game", 2); // Make the screen and change the int value
        this.constState = new ScreenChangeButton(new Rect(x/5, (int)(y*0.45), (int)(x*0.8), (int)(y*0.55)), "Tests", 3);

        this.buttons = new Button[]{charSelect, newGame, constState};
    }


}
