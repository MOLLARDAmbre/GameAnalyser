package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.GraphicObject.Popup;

/**
 * A button made to close a popup properly
 */
public class PopupCloseButton extends Button {

    private Popup parent;

    public PopupCloseButton(Rect r, Popup parent){
        super(r, "Close");
        this.parent = parent;
    }

    public void run() {
        this.parent.close();
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(150,150,150));
        super.draw(c, paint);
    }
}
