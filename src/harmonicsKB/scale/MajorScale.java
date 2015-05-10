public abstract class MajorInterval extends AbstractScale {
	public MajorScale() {
		super();
	}

	protected int [] buildIntervals() {
		return new int []{2,2,1,2,2,2,1};
	}
}