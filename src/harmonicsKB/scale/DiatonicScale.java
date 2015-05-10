import java.util.ArrayList;

public class DiatonicScale extends Scale {
	
	public DiatonicScale(Note root) {
		super(root);
	}

	protected ArrayList<Note> buildScale() {
		ArrayList<Note> diatonicScale = new ArrayList<Note>(8);
		int[] steps = new int []{2,2,1,2,2,2,1};
		int noteIndex = root.getNoteIndex();
		diatonicScale.add(root);

		for (int i = 0; i < 7; i++) {
			noteIndex += steps[i];
			diatonicScale.add(new Note(noteIndex));
		}

		return diatonicScale;
	}

	public static void main(String [] args) {
		try {
			Note note = new Note("B");
			DiatonicScale cs = new DiatonicScale(note);
			 ArrayList<Note> scale = cs.getScaleAsString();
			 for (Note n : scale) System.out.println(n);
		} catch (Exception e) { e.printStackTrace(); }
	}
}