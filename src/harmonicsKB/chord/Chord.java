package data_objects;

import utils.LimitedQueue;
import virtuouso.Degree;
import virtuouso.Mode;

import java.util.List;

/**
 * Created by ben on 5/9/2015.
 */
public abstract class Chord {
    Mode mode;
    LimitedQueue<Degree> chordDegrees;

    protected abstract LimitedQueue constructDegrees();

    public List constructChordTones(String keyTonic, String root) {
        return null;
    }
}
