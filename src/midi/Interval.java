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