package data_objects;

import virtuouso.Degree;

/**
 * Created by ben on 5/10/2015.
 */
public class Minor7thChord extends MajorChord {

    public Minor7thChord() {
        super();
        chordDegrees.add(Degree.Leading);
    }
}
