package learn.nyx.com.gameanalyser.Screens;
import java.util.ArrayList;
import java.util.List;

import learn.nyx.com.gameanalyser.Constants;;
import learn.nyx.com.gameanalyser.Screens.GraphicObject.Event;

/**
 * A class which will manage what screen to display. It should be interacted with through buttons,
 * You can also create rules to twist how it works by twisting setScreen(int n)
 */
public class ScreenManager {

    private Screen greetingScreen; //0
    private Screen charSelect; //1
    private Screen testScreen; // 3
    private GameScreen gameScreen; // 2
    private DisplayScreen displayScreen; // 4
    private Screen constantsScreen; //Not referenced

    private List<Screen> screenList;
    private int currentScreen;


    public ScreenManager() {
        this.greetingScreen = new GreetingScreen();
        this.charSelect = new CharacterSelectScreen();
        this.testScreen = new TestScreen();
        this.gameScreen = new GameScreen();
        this.constantsScreen = new ConstantsScreen();
        this.displayScreen = new DisplayScreen(new ArrayList<Event>());

        this.screenList = new ArrayList<Screen>();
        this.screenList.add(greetingScreen);
        this.screenList.add(charSelect);
        this.screenList.add(gameScreen);
        this.screenList.add(testScreen);
        this.screenList.add(displayScreen);
        //this.screenList.add(constantsScreen);
        this.currentScreen = 0;
    }

    public void setScreen(int n) {

        if (n != 2) {
            this.currentScreen = n;
        }
        else {
            if ((Constants.player1 == null) || (Constants.player2 == null)) {
                this.currentScreen = 1;
            }
            else {
                this.currentScreen = 2;
            }
        }
    }

    public Screen getCurrentScreen() {
        return screenList.get(currentScreen);
    }

    public void UpdateEvLists(List<Event> l) {
        this.displayScreen.debugText = "";
        this.displayScreen.updateEvList(l);
        this.gameScreen.updateEvList(l);
    }

}
