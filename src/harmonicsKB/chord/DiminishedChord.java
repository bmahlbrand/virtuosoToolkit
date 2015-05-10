package data_objects;

import utils.LimitedQueue;
import virtuouso.Degree;
import virtuouso.Mode;

/**
 * Created by ben on 5/10/2015.
 */
public class DiminishedChord extends Chord {
    public DiminishedChord() {
        mode = Mode.Locrian;
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
