package learn.nyx.com.gameanalyser;

import android.content.Context;

import learn.nyx.com.gameanalyser.Structure.Character;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Popup;
import learn.nyx.com.gameanalyser.Screens.ScreenManager;
import learn.nyx.com.gameanalyser.Structure.Data;

/**
 * A class containing values accessible between all activities
 */
public class Constants {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static Context CONTEXT;
    public static Character player1;
    public static Character player2;
    public static String player1Name;
    public static String player2Name;
    public static ScreenManager MANAGER;
    public static boolean POPUP_OPEN;
    public static Popup POPUP;
    public static Event POPUP_EVENT;
    public static Data DATA;

}
