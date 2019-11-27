package learn.nyx.com.gameanalyser.Structure;

/**
 * This allows to define a situation
 */
public class Situation {


    private String name;

    public Situation(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        try {
            return ((Situation) o).toString() == this.toString();
        }
        catch (Exception e) {
            return false;
        }
    }

}
