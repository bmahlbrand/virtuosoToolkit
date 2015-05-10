package data_objects;

import utils.LimitedQueue;
import virtuouso.Degree;
import virtuouso.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 5/10/2015.
 */
public class MajorChord extends Chord {

    public MajorChord() {
        mode = Mode.Ionian;
        chordDegrees = constructDegrees();
    }

    @Override
    protected LimitedQueue constructDegrees() {
        LimitedQueue<Degree> degrees = new LimitedQueue<>(3);
        degrees.add(Degree.Tonic);
        degrees.add(Degree.Mediant);
        degrees.add(Degree.Dominant);
        return degrees;
    }
}
