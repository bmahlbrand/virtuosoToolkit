package data_objects;

import utils.LimitedQueue;
import virtuouso.Degree;
import virtuouso.Mode;

/**
 * Created by ben on 5/10/2015.
 */
public class Major7thChord extends MajorChord {

    public Major7thChord() {
        super();
        chordDegrees.add(Degree.Leading);
    }
}
