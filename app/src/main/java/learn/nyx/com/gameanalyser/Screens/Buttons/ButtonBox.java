package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * A box to display several buttons at once
 */
public class ButtonBox {

    private Button[] buttons;
    private Rect space;

    public ButtonBox(Rect s, Button[] b) {
        this.space = s;
        this.buttons = b;
    }

    public Rect getSpace() {
        return this.space;
    }

    public boolean onTouchEvent(MotionEvent e) {

        for (Button b : buttons) {
            b.setCol(Color.WHITE);
            if (b.getRect().contains((int)(e.getX()), (int)(e.getY()))) {
                b.run();
            }
        }
        return true;
    }
    public void draw(Canvas c){
        Paint paint = new Paint();
        paint.setColor(Color.rgb(150,150,150));
        c.drawRect(this.space, paint);
        for (Button b : this.buttons) {
            b.draw(c);
        }
    }

}
