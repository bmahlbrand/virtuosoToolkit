import java.io.*;
import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Parser {
	public static final int NOTE_ON = 0x90;
	public static final int NOTE_OFF = 0x80;

	public static void main(String[] args) throws Exception {
		// final File folder = new File("music");
		// buildDatabase(folder);
		int note = Parser.noteFromFrequency(110);
		System.out.println(note + " " + Note.noteIndexToString(note));
		note = Parser.noteFromFrequency(220);
		System.out.println(note + " " + Note.noteIndexToString(note));
		note = Parser.noteFromFrequency(440);
		System.out.println(note + " " + Note.noteIndexToString(note));
		note = Parser.noteFromFrequency(880);
		System.out.println(note + " " + Note.noteIndexToString(note));

		Parser.autoCorrelation(512);

	}

	public static byte[] rawAudioData() throws Exception{
		File wavFile = new File("test.wav");
		AudioInputStream inputAIS = AudioSystem.getAudioInputStream(wavFile);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int nBufferSize = (int)wavFile.length();
		byte[]	abBuffer = new byte[nBufferSize];
		while (true)
		{
			// if (DEBUG) { out("trying to read (bytes): " + abBuffer.length); }
			int	nBytesRead = inputAIS.read(abBuffer);
			// if (DEBUG) { out("read (bytes): " + nBytesRead); }
			if (nBytesRead == -1)
			{
				break;
			}
			baos.write(abBuffer, 0, nBytesRead);
		}

		/* Here's the byte array everybody wants.
		 */
		byte[] abAudioData = baos.toByteArray();

		/* And now, write it to a file again.
		 */
		// ByteArrayInputStream bais = new ByteArrayInputStream(abAudioData);
		// AudioInputStream outputAIS = new AudioInputStream(
		// 	bais, audioFormat,
		// 	abAudioData.length / audioFormat.getFrameSize());
		// int	nWrittenBytes = AudioSystem.write(outputAIS,
		// 					  targetFileType,
		// 					  targetFile);
		// // if (DEBUG) { out("Written bytes: " + nWrittenBytes); }

		return abAudioData;

	}
	
	public static void autoCorrelation(int size) throws Exception{
	    float[] R = new float [size];
	    float sum = 0;
	    byte [] bytes = rawAudioData();
	    float [] x = new float [size];
	    for (int i = 0; i < size; i++)
	    	x[i] = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();

	    for (int i=0;i<size;i++) {
	        sum=0;
	        for (int j=0;j<size-i;j++) {
	            sum+=x[j]*x[j+i];
	        }
	        R[i]=sum;

	        System.out.println(sum);
	    }
	}
	//Takes all the files in the folder called "music", parse into parsons code,
	//and create a database file.
	public static void buildDatabase(final File folder) throws Exception {
		Writer writer = null;
		int i = 1;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("database.txt"), "utf-8"));
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					buildDatabase(fileEntry);
				} else {
					if (fileEntry.getName().endsWith(".wav")){ continue; }
					writer.write(i+", "+parsonCode("music/"+fileEntry.getName())+", "+ fileEntry.getName()+"\n");
					i++;
				}
			}
		} catch (IOException ex) {
			// report
		} finally {
			try {writer.close();} catch (Exception ex) {}
		}
	}
	
	public static String parsonCode(String filename) throws Exception {
		File music_file = new File(filename);
		int previous_key = 0;
		int trackNumber = 0;
		int firstnote = 0;
		StringBuilder ret = new StringBuilder();
		Sequence sequence = MidiSystem.getSequence(music_file);

		for (Track track :  sequence.getTracks()) {
			trackNumber++;
			for (int i=0; i < track.size(); i++) { 
				MidiEvent event = track.get(i);
				MidiMessage message = event.getMessage();
				if (message instanceof ShortMessage) {
					ShortMessage sm = (ShortMessage) message;
					if (sm.getCommand() == NOTE_ON) {
						if(firstnote == 0) {
							ret.append("*");
							firstnote = 1;
							int key = sm.getData1();
							previous_key=key;
						} else {
							int key = sm.getData1();
							if(key == previous_key)
								ret.append("R");
							else if(key>previous_key)
							  	ret.append("U");
							else
								ret.append("D");
							
							previous_key = key;
						}
					}
				}
			}
		}
		return ret.toString();
	}

	public static String parsonCodeFromWav(String fileName) {
		if (fileName.endsWith(".mid") || fileName.endsWith(".wav")) {
			System.out.println("parsonCodeFromWav ::: ERROR wrong type");
		}

		System.out.println("===================================");
		System.out.println("parsonCodeWav: " + fileName);
		BufferedReader reader = null;
		StringBuilder parsonCode = null;
		String line = null;
		float previousKey = 0;

		try {
			reader = new BufferedReader((new FileReader(fileName)));
			parsonCode = new StringBuilder();
			
			while ((line = reader.readLine()) != null) {
				String[] trimmedLine = line.split(" ");
				float duration = 0;
				float frequency = 0;
				int i = 0;

				for (String s : trimmedLine) {
					if (i == 0) {
						duration = Float.parseFloat(s); 
						i++;
					} else {
						frequency = Float.parseFloat(s);
						if (frequency == 0) {
							continue;
						} else if (Math.abs(frequency - previousKey) < 0.1) {
							parsonCode.append("R");
						} else if (frequency - previousKey > 3) { 
							parsonCode.append("U");
						} else if (previousKey - frequency > 3){
							parsonCode.append("D");
						}

						previousKey = frequency;
					}
				}
			}   

		} catch(IOException e) {
			e.printStackTrace();    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {try{reader.close();} catch(Exception e) {}}

		return parsonCode.toString();
	}

	public static int noteFromFrequency(float freq) {

		//f = 440 * 2^(n/12)
		System.out.println(69 + 12 * Math.log(freq / 440)/Math.log(2));
		return (int)(69 + 12 * Math.log(freq / 440)/Math.log(2));
	}

	public int notesFromWav(String fileName) {
		return 0;
	}
}