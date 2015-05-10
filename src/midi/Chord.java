import java.util.ArrayList;

public class Chord {
	private Note root;
	private ArrayList<Note> notes;
	
	public Chord(Note root) {
		this.root = root;
		DiatonicScale ds = new DiatonicScale(root);
		notes = new ArrayList<Note>();
		notes.add(ds.getNote(0));
		notes.add(ds.getNote(2));
		notes.add(ds.getNote(4));
	}

	public Note getRoot() {
		return root;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}
}