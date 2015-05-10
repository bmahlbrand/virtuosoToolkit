public class Note {
	
	public static void main(String [] args) {
		assert(Note.calcOctave(noteFromFrequency(440f)) == 4);
		System.out.println(Note.calcOctave(noteFromFrequency(440f)));
		assert(Note.calcOctave(noteFromFrequency(16.35f)) == 0);
		System.out.println(Note.calcOctave(noteFromFrequency(16.35f)));
		System.out.println(Note.calcOctave(noteFromFrequency(41.2f)));
		assert(Note.calcOctave(noteFromFrequency(41.2f)) == 1);
		assert(Note.calcOctave(noteFromFrequency(130.81f)) == 3);

		assert(noteIndexToString(Note.noteFromFrequency(440f)).equals("A"));

		System.out.println(noteIndexToString(Note.noteFromFrequency(440f)));
		System.out.println(noteIndexToString(Note.noteFromFrequency(467)));
		System.out.println(noteIndexToString(Note.noteFromFrequency(500f)));
		System.out.println(Note.noteToFrequency(68));
	}

	String tone = null;
	int noteIndex = -1;
	int octave = -1;

	public Note(String note) {
		this.tone = note;
		this.noteIndex = noteIndex(note);
		this.octave = 5;
	}

	public Note(int note) {
		this.tone = noteIndexToString(note);
		this.noteIndex = note;
		this.octave = 5;
	}

	public Note(String note, int octave) {
		this.tone = note;
		this.noteIndex = noteIndex(note);
		this.octave = octave;
	}

	public Note(int note, int octave) {
		this.tone = noteIndexToString(note);
		this.noteIndex = note;
		this.octave = octave;
	}

	static String noteIndexToString(int note) {
		if (note > 11) note = note % 12;

		switch(note) {
			case 0:
				return "C";
			case 1:
				return "C#";
			case 2:
				return "D";
			case 3:
				return "D#";
			case 4:
				return "E";
			case 5:
				return "F";
			case 6:
				return "F#";
			case 7:
				return "G";
			case 8:
				return "G#";
			case 9:
				return "A";
			case 10:
				return "A#";
			case 11:
				return "B";
		}

		return null;
	}

	static int noteIndex(String note) {
		switch(note) {
			case "C":
				return 0;
			case "C#":
				return 1;
			case "D":
				return 2;
			case "D#":
				return 3;
			case "E":
				return 4;
			case "F":
				return 5;
			case "F#":
				return 6;
			case "G":
				return 7;
			case "G#":
				return 8;
			case "A":
				return 9;
			case "A#":
				return 10;
			case "B":
				return 11;
		}

		return -1;
	}

	public int getNoteIndex() {
		return this.noteIndex;
	}

	public String toString() {
		return this.tone;
	}
	static int noteShift(int note, int shift) {
		int offset = 0;
		if (note + shift > 11) {
			offset = note + shift - 11;
		}

		return offset;
	}

	static String getTone(String note) {
		if (note.equals("B") || note.equals("E")) {
			return noteIndexToString((noteIndex(note) + 1));
		} else { 
			return noteIndexToString((noteIndex(note) + 2)); 
		}
	}

	public Note getSemitone() {
		return new Note(noteIndex + 1); 
		// noteIndexToString((byte)(noteIndex(note) + 1));
	}

	int midiNoteIndex() {
		return this.noteIndex + 12 * this.octave;
	}

	public static double round(double value) {
		return Math.round(value * 100) / 100;
	}

	public static int noteFromFrequency(double freq) {
		double result = 12f * Math.log(freq / 440f) / Math.log(2);
		return (int)(69 + Math.round(result));
	}

	public static double noteToFrequency(int note) {
		return Math.pow(2, (note - 69f) / 12f) * 440f;
	}

	public static double calcOctave(int note) {
		return Math.floor((note - 12f) / 12f);
	}
}