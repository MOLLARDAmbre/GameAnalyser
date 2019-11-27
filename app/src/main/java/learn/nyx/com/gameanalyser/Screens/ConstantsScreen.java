package learn.nyx.com.gameanalyser.Screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Screens.Buttons.ScreenChangeButton;

/**
 * A testing screen which allow to look at the values stored in Constants
 */
public class ConstantsScreen extends Screen {

    private Button back;


    public ConstantsScreen() {

        this.back = new ScreenChangeButton(new Rect(0, 0, Constants.SCREEN_WIDTH / 5, Constants.SCREEN_HEIGHT / 20), "Back", 0);
        this.buttons = new Button[]{back};

    }



    public void draw(Canvas c) {
        super.draw(c);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        c.drawText(Constants.player1Name, 100, 100, paint);
        c.drawText(Constants.player2Name, 100, 200, paint);
        c.drawText(Constants.player1.toString(), 100, 300, paint);
        c.drawText(Constants.player2.toString(), 100, 400, paint);
    }


}
