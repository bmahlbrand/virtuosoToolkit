public abstract class AbstractScale {
	private int intervals [] = null;

	public AbstractScale() {
		intervals = buildIntervals();
	}

	protected abstract int [] buildIntervals();
}