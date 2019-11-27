package learn.nyx.com.gameanalyser.Structure;


import java.util.ArrayList;
import java.util.List;

/**
 * This represents an option chosen by the player in the given situation
 */
public class Option {

    private String name;

    public String toString() {
        return this.name;
    }

    public Option(String name) {
        this.name = name;
    }

    public static List<Option> generateOpts(String[] opts) {
        ArrayList<Option> l = new ArrayList<>();
        for (String s : opts) {
            l.add(new Option(s));
        }
        return l;
    }

}
