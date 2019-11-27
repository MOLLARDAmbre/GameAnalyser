package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import android.graphics.Color;

import java.util.List;
import learn.nyx.com.gameanalyser.Structure.Option;

/**
 * This clas is what will allow a situation to have a color for each option chosen
 */
public class ColorWheel {

    private int nbOptions;
    private List<Option> l;

    public ColorWheel(List<Option> l) {
        this.nbOptions = l.size();
        this.l = l;
    }

    public int getColor(Option o) {
        int i = -1;
        for (int j = 0; j < l.size(); j++) {
            if (o.toString() == l.get(j).toString()) {
                if (i==-1) {
                    i = j;
                }
            }
        }
        return Color.HSVToColor(new float[]{(250+200*i)/nbOptions,1,1});
    }

    public void add(Option o) {
        this.l.add(o);
        this.nbOptions += 1;
    }

    public int getNbOptions() {
        return this.nbOptions;
    }

}
