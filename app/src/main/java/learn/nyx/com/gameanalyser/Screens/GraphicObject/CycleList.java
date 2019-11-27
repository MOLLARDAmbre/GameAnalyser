package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import java.util.ArrayList;


/**
 * A list of Events which will cycle (its element number -1 will be its last)
 */
public class CycleList<T> extends ArrayList<T> {

    public T get(int index) {
        if (index >= this.size()) {
            return this.get(index-this.size());
        }
        if (index < 0) {
            return this.get(index+this.size());
        }
        return super.get(index);
    }


}
