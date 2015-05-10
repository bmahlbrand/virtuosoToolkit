import java.util.ArrayList;

public abstract class Scale {
	Note root;
	ArrayList<Note> scale;
	int intervals [] = null;

	Scale(Note root) {
		this.root = root;
		this.intervals = buildIntervals();
		this.scale = buildScale();
	}

	public Note getNote(Degree degree) {
		if (this.scale == null) {
			this.buildScale();
		}

		return this.getNote(degree.toInt());
	}

	public ArrayList<Note> getScaleAsString() {
		if (this.scale == null) {
			this.buildScale();
		} 

		return this.scale;
	}

	public Note getNote(int degree) {
		return this.scale.get(degree);
	}

	protected abstract ArrayList<Note> buildScale();
	protected abstract int [] buildIntervals();
}