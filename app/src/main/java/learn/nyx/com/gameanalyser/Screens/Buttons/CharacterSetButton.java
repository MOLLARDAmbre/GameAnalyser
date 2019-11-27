package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Structure.Character;

public class CharacterSetButton extends ScreenChangeButton {


    public CharacterSetButton(Rect r, String l, int n) {
        super(r, l, n);
    }

    public void run() {
        super.run();
        try {
            Constants.player1 = new Character(Constants.player1Name);
            Constants.player2 = new Character(Constants.player2Name);
        }
        catch(Exception e) {

        }
    }
}
