package learn.nyx.com.gameanalyser.Screens.Buttons;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * This class represents a Button, which can be clicked on to launch the run() method
 */
public abstract class Button {

    private Rect rect;
    public int left;
    public int top;
    public int bottom;
    public int right;
    private String label;

    public Button(Rect r, String l) {
        this.rect = r;
        this.top = r.top;
        this.bottom = r.bottom;
        this.left = r.left;
        this.right = r.right;
        this.label = l;
    }
    // TODO Implement Button(Rect r, Bitmap b) and the draw function

    public abstract void run();

    public Rect getRect() {
        return this.rect;
    }

    public void setRect(Rect r) {
        this.rect = r;
        this.top = r.top;
        this.left = r.left;
        this.right = r.right;
        this.bottom = r.bottom;
    }

    /**
     * Generic draw funciton
     * @param c the canvas to draw on
     */
    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        c.drawRect(this.getRect(), paint);
        paint.setColor(Color.BLACK);
        String label = this.label;
        int x0 = this.left;
        int x1 = this.right;
        int y0 = this.top;
        int y1 = this.bottom;

        int x = x0 + (x1-x0)/20;
        int y = y1 - (y1-y0)/3;

        int x_var = (int)((x1-x0)/label.length() * 1.5);
        int y_var = (int)((y1-y0)*0.7);

        int var = min(x_var, y_var);
        paint.setTextSize(var);

        c.drawText(label, x, y, paint);

    }

    /**
     * Draws with a specific color
     * @param c the canvas
     * @param paint the paint representing the color
     */
    public void draw(Canvas c, Paint paint) {
        c.drawRect(this.getRect(), paint);
        paint.setColor(Color.BLACK);
        String label = this.label;
        int x0 = this.left;
        int x1 = this.right;
        int y0 = this.top;
        int y1 = this.bottom;

        int x = x0 + (x1-x0)/20;
        int y = y1 - (y1-y0)/3;

        int x_var = (int)((x1-x0)/label.length() * 1.5);
        int y_var = (int)((y1-y0)*0.7);

        int var = min(x_var, y_var);
        paint.setTextSize(var);
        c.drawText(label, x, y, paint);
    }

    private int min(int x_var, int y_var) {
        if (x_var < y_var) {
            return x_var;
        }
        return y_var;
    }

    /**
     * Moves the button down
     * @param nb represents how many pixels we want to move our button of
     */
    public void down(float nb) {
        float d = nb;
        this.top += d;
        this.bottom += d;
        this.rect.top += d;
        this.rect.bottom += d;
    }

    /**
     * Moves the button up
     * @param nb represents how many pixels we want to move our button of
     */
    public void up(float nb) {
        float d = nb;
        this.top -= d;
        this.bottom -= d;
        this.rect.top -= d;
        this.rect.bottom -= d;
    }


    public void setCol(int i) {
        // Compatibility reasons for this function to exist
    }

}
