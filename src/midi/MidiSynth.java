import javax.sound.midi.*;
import java.util.ArrayList;
public class MidiSynth {
	Synthesizer synth = null;
	MidiChannel[] mc = null;

	public MidiSynth() throws Exception {
		// synth = MidiSystem.getSynthesizer();
		
	}

	void addTrack() {
		// Track track = createTrack();
	}

	void removeTrack(Track track) {
		// deleteTrack(track);
	}

	void addNote(int ch) {

		// t0.add(new MidiEvent(middleC()));
	}

	void riff(String note) throws Exception{
		while (true) {
			playChord(note);
			Thread.sleep(200);
			// playChord(Scales.getWholeStep(note));
			// playChord(Scales.getWholeStep(Scales.getWholeStep(note)));
		}
	}

	void playChord(String note) throws Exception {
		synth = MidiSystem.getSynthesizer();

		synth.open();
		mc = synth.getChannels();
		// byte [] scale = Scales.majorChord(note);
		Instrument[] instr = synth.getAvailableInstruments();
    	synth.loadInstrument(instr[82]);

    	Chord ch = new Chord(new Note(note));
    	ArrayList<Note> notes = ch.getNotes();

		mc[0].noteOn(notes.get(0).midiNoteIndex(), 120);
		mc[0].noteOn(notes.get(1).midiNoteIndex(), 120);
		mc[0].noteOn(notes.get(2).midiNoteIndex(), 120);
		Thread.sleep(100);
		mc[0].allNotesOff();
	}

	void playScale(String note) throws Exception {
		synth = MidiSystem.getSynthesizer();

		synth.open();
		mc = synth.getChannels();

		Instrument[] instr = synth.getAvailableInstruments();
    	synth.loadInstrument(instr[82]);

		ChromaticScale cs = new ChromaticScale(new Note(note));
		int duration = 200;

		for (int i = 0; i < 12; i++) {
			Thread.sleep(duration);
			mc[0].noteOn(cs.getNote(i).midiNoteIndex() - 11, 120);
		}

		for (int i = 0; i < 12; i++) {
			Thread.sleep(duration);
			mc[0].noteOn(cs.getNote(i).midiNoteIndex(), 120);
		}

		for (int i = 0; i < 12; i++) {
			Thread.sleep(duration);
			mc[0].noteOn(cs.getNote(i).midiNoteIndex() + 11, 120);
		}

		for (int i = 0; i < 12; i++) {
			Thread.sleep(duration);
			mc[0].noteOn(cs.getNote(i).midiNoteIndex() + 22, 120);
		}

		Thread.sleep(5000);
		mc[0].allNotesOff();

		riff(note);
	}

	public static void main(String[] args) throws Exception {
		MidiSynth md = new MidiSynth();
    	int duration = 200;
    	md.playScale("E");
  //   	Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
  //   	synth.loadInstrument(instr[90]);
  //   	byte [] chord = Scales.majorChord("C");
		// mc[0].noteOn(Scales.midiNoteIndex(chord[0]), 120);
		// Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[1]), 120); // E
		// Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[2]), 120); // G
		// Thread.sleep(duration);
		// Thread.sleep(3000);
		// mc[0].allNotesOff();

		// chord = Scales.majorChord("D");
		// mc[0].noteOn(Scales.midiNoteIndex(chord[0]), 120);
		// Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[1]), 120); // E
		// Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[2]), 120); // G
		// Thread.sleep(duration);
		// Thread.sleep(3000);
		// mc[0].allNotesOff();

		// chord = Scales.majorChord("E");
		// mc[0].noteOn(Scales.midiNoteIndex(chord[0]), 120);
		// // Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[1]), 120); // E
		// // Thread.sleep(duration);
		// mc[0].noteOn(Scales.midiNoteIndex(chord[2]), 120); // G
		// Thread.sleep(duration);
		// Thread.sleep(3000);
		// mc[0].allNotesOff();
    }
}