public enum Degree {

    TONIC       (0),
    SUPERTONIC  (1),
    MEDIANT     (2),
    SUBDOMINANT (3),
    DOMINANT    (4),
    SUBMEDIANT  (5),
    LEADING     (6);

    private int degree;

    private Degree(int degree) {
        this.degree = degree;
    }

    public int toInt() {
        return this.degree;
    }
}