import javax.sound.midi.*;

public class Scales {
	
	static byte[] CMajor() {
		String []notes = "C D E F G A B".split(" ");
		byte [] scale = new byte[7];

		for (int i = 0; i < 7; i++) {
			scale[i] = noteIndex(notes[i]);
		}

		return scale;
	}

	static String AMinor() {
		return "A B C D E F G";
	}

	static String noteIndexToString(byte note) {
		if (note > 11) note = (byte)(note % 11);

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

	static byte noteIndex(String note) {
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

	static byte noteShift(byte note, byte shift) {
		byte offset = 0;
		if (note + shift > 11) {
			offset = (byte)(note + shift - 11);
		}

		return offset;
	}

	static String getWholeStep(String note) {
		if (note.equals("B") || note.equals("E")) {
			return noteIndexToString((byte)(noteIndex(note) + 1));
		} else { 
			return noteIndexToString((byte)(noteIndex(note) + 2)); 
		}
	}

	static String getHalfStep(String note) {
		return noteIndexToString((byte)(noteIndex(note) + 1));
	}

	static byte midiNoteIndex(byte note) {
		return (byte)(note + 60);
	}

	static String majorScale(String note) {
		StringBuilder scale = new StringBuilder();
		byte noteIndex = noteIndex(note);
		byte [] majorSteps = {2,2,1,2,2,2,1};

		for (int i = 0; i < 7; i++) {
			scale.append(noteIndexToString(noteIndex));
			scale.append(" ");
			
			switch (majorSteps[i]) {
				case 1:
					noteIndex = noteIndex(getHalfStep(noteIndexToString(noteIndex)));
					break;
				case 2:
					noteIndex = noteIndex(getWholeStep(noteIndexToString(noteIndex)));
					break;
			}
			
		}

		return scale.toString();
	}

	static byte [] majorScaleAsBytes(String note) {
		byte noteIndex = noteIndex(note);
		byte [] majorSteps = {2,2,1,2,2,2,1};
		byte [] scale = new byte[7];

		for (int i = 0; i < 7; i++) {
			scale[i] = midiNoteIndex(noteIndex);
			noteIndex += majorSteps[i];
		}

		return scale;
	}

	static String minorScale(String note) {
		StringBuilder scale = new StringBuilder();
		byte noteIndex = noteIndex(note);
		byte [] minorSteps = {2, 1, 2, 2, 1, 2, 2};

		for (int i = 0; i < 7; i++) {
			scale.append(noteIndexToString(noteIndex));
			scale.append(" ");
			switch (minorSteps[i]) {
				case 1:
					noteIndex = noteIndex(getHalfStep(noteIndexToString(noteIndex)));
					break;
				case 2:
					noteIndex = noteIndex(getWholeStep(noteIndexToString(noteIndex)));
					break;
			}
		}

		return scale.toString();
	}

	static byte[] majorChord(String note) {
		int i = 0;
		byte noteIndex = noteIndex(note);
		byte [] chord = new byte[3];
		while (i < 3) {
			chord[i] = noteIndex;
			if ((noteIndex - 11 == 0) || (noteIndex - 4 == 0)) {
				noteIndex += 3;
			} else {
				noteIndex += 4;
			}

			i++;
		}

		System.out.println(noteIndexToString(chord[0]) + " " + midiNoteIndex(chord[0]) + " " 
			+ noteIndexToString(chord[1]) + " " + midiNoteIndex(chord[1]) + " " + noteIndexToString(chord[2]) + " " + midiNoteIndex(chord[2]));
		return chord;
	}

	static byte[] minorChord(String note) {
		int i = 0;
		byte noteIndex = noteIndex(note);
		byte [] chord = new byte[3];
		while (i < 3) {
			chord[i] = noteIndex;
			// if ((noteIndex - 11 == 0) || (noteIndex - 4 == 0)) {
				noteIndex += 1;
			// }
			 // else {
				noteIndex += 2;
			// }

			i++;
		}

		System.out.println(midiNoteIndex(chord[0]) + " " + midiNoteIndex(chord[1]) + " " + midiNoteIndex(chord[2]));
		return chord;
	}

	public static void main (String[] args) {
		System.out.println(noteIndex("A"));
		byte [] arr = majorChord("A");
		System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
		System.out.println("C major:" + majorScale("C"));
		System.out.println("B major:" + majorScale("B"));
		System.out.println("B minor:" + minorScale("B"));
	}
}