import java.util.ArrayList;

public class ChromaticScale<T> extends Scale {
	public ChromaticScale(Note root) {
		super(root);
	}

	public int [] buildIntervals() {
		return new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
	}

	protected ArrayList<Note> buildScale() {
		ArrayList<Note> chromaticScale = new ArrayList<Note>(12);
		int noteIndex = root.getNoteIndex();
		
		chromaticScale.add(root);
		
		for (int i = 0; i < 12; i++) {
			noteIndex += this.intervals[i];
			chromaticScale.add(new Note(noteIndex));
		}
		
		return chromaticScale;
	}

	public static void main(String[] args) {
		try {
			Note note = new Note("C");
			ChromaticScale cs = new ChromaticScale(note);
			ArrayList<Note> scale = cs.getScaleAsString();
			for (Note n : scale) System.out.println(n);
		} catch (Exception e) { e.printStackTrace(); }
	}
}