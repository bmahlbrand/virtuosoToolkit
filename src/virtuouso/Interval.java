package virtuouso;

/**
 * Created by ben on 5/10/2015.
 */
public enum Interval {
    Tone(2),
    Semitone(1);

    private int semitones;

    private Interval(int semitones) {
        this.semitones = semitones;
    }

    public int toSemitones() {
        return semitones;
    }
}
