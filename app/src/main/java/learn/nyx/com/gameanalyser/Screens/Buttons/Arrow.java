package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.GameScreen;

/**
 * This is a button that looks like an arrow, it is used in the GameScreen to change which element to display
 */
public class Arrow extends Button {

    private int dir; //0 means left, 1 means right
    private GameScreen parent;

    public Arrow(int dir, Rect pos, GameScreen parent) {
        super(pos, " ");
        this.dir = dir;
        this.parent = parent;
    }

    public void run() {
        if (this.dir == 0) {
            parent.left();
        }
        else {
            parent.right();
        }
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        super.draw(c, paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        Path path = new Path();
        if (this.dir == 0) {
            path.moveTo(this.right, this.bottom);
            path.lineTo(this.left, (this.bottom+this.top)/2);
            path.lineTo(this.right, this.top);
            path.lineTo(this.right, this.bottom);
        }
        else {
            path.moveTo(this.left, this.bottom);
            path.lineTo(this.right, (this.bottom+this.top)/2);
            path.lineTo(this.left, this.top);
            path.lineTo(this.left, this.bottom);
        }
        path.close();
        c.drawPath(path, paint);
    }

}
