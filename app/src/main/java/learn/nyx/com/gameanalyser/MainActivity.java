package learn.nyx.com.gameanalyser;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import learn.nyx.com.gameanalyser.Structure.Data;
import learn.nyx.com.gameanalyser.Structure.Initializer;

/**
 * The main activity which launches everything
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.CONTEXT = this;
        Constants.POPUP_OPEN = false;

        Constants.player1Name = "Player 1";
        Constants.player2Name = "Player 2";


        Constants.DATA = new Data();
        Initializer.initialize();
        // TODO : Load situations, options and characters from a file
        // TODO Also allow saving games as an evList


        // TODO find a better color palette for Events


        setContentView(new AppPanel(this));



    }

}
