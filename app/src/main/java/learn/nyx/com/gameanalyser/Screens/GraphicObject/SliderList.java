package learn.nyx.com.gameanalyser.Screens.GraphicObject;

import java.util.ArrayList;

/**
 * Don't know where this is called
 */
public class SliderList extends ArrayList<SlidingPannel2> {

    public SlidingPannel2 get(int index) {
        if (index >= this.size()) {
            return this.get(index-this.size());
        }
        if (index < 0) {
            return this.get(index+this.size());
        }
        return super.get(index);
    }

}
