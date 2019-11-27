package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * This is a button which will turn yellow the first time you click on it, and will run when you click on it a second time
 */
public class ConfirmButton extends Button {

    private boolean isClicked;
    private int col;


    public ConfirmButton(Rect r, String label) {
        super(r, label);
        this.col = Color.WHITE;
        this.isClicked = false;
    }

    public void run() {
        if (this.isClicked) {
            this.click();
        }
        else {
            this.isClicked = true;
            this.col = Color.YELLOW;
        }
        return;
    }

    public void click(){};

    public void setCol(int i) {
        this.col = i;
    }


    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(this.col);
        super.draw(c, paint);
    }
}
