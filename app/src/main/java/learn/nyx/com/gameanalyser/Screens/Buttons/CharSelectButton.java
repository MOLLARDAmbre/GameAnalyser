package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


import learn.nyx.com.gameanalyser.Structure.Character;
import learn.nyx.com.gameanalyser.Constants;

/**
 * Button made for Character selection
 */
// TODO make it extend confirm button
public class CharSelectButton extends Button {


    private int player;
    private String character;
    private int col;

    public CharSelectButton(int player, String c) {
        super(new Rect(0,0,0,0), c.toString());
        this.col = Color.WHITE;
        this.player = player;
        this.character = c;
    }

    public void run() {
        if (player == 1) {
            Constants.player1Name = this.character;
        }
        else {
            Constants.player2Name = this.character;
        }
        this.col = Color.YELLOW;
    }

    public String playerName() {
        if (this.player == 1) {
            return Constants.player1Name;
        }
        else {
            return Constants.player2Name;
        }
    }


    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(this.col);
        super.draw(c, paint);
    }

    public void setCol(int i) {
        this.col = i;
    }

}
