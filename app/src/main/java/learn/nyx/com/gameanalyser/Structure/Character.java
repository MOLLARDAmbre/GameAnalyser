package learn.nyx.com.gameanalyser.Structure;

import android.graphics.Bitmap;
import android.graphics.Path;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import learn.nyx.com.gameanalyser.Constants;
import learn.nyx.com.gameanalyser.Screens.Buttons.Button;
import learn.nyx.com.gameanalyser.Structure.Option;

public class Character {

    protected String name;

    protected ArrayList<ArrayList<Option>> options;
    protected Bitmap picture; // TODO

    public Character(String name) {
        this.name = name;
        this.options = Constants.DATA.getOptions(this);
    }

    public String toString() {
        return this.name;
    }

    public List<Option> getOptions(Situation sit) {
        return this.options.get(Constants.DATA.getSituations().indexOf(sit));
    }

}
