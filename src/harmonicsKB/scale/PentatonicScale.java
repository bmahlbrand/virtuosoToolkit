import java.util.ArrayList;

public class PentatonicScale extends Scale {
	
	public PentatonicScale(Note root) {
		super(root);
	}

	protected ArrayList<Note> buildScale() {
		ArrayList<Note> pentatonicScale = new ArrayList<Note>(5);
		int steps[] = new int []{2,2,3,2,3};
		int noteIndex = root.getNoteIndex();

		pentatonicScale.add(root);
		for (int i = 0; i < 5; i++) {
			noteIndex += steps[i];
			pentatonicScale.add(new Note(noteIndex));
		}

		// pentatonicScale.add(root);
		
		return pentatonicScale;
	}
	
	public static void main(String [] args) {
		try {
			Note note = new Note("E");
			PentatonicScale cs = new PentatonicScale(note);
			ArrayList<Note> scale = cs.getScaleAsString();
			for (Note n : scale) 
				System.out.println(n);
		} catch (Exception e) {e.printStackTrace();}
	}
}