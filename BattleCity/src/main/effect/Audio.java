
package main.effect;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * The sound effect for tank explosion.
 */
public class Audio {
	private AudioFormat audioFormat = null;
	private SourceDataLine sourceDataLine = null;
	private DataLine.Info dataLine_info = null;

	private AudioInputStream audioInputStream = null;

	/**
	 * Initialize the audio effect
	 * @param fileName
	 */
	public Audio(String fileName) {
		try {
			// load the audio file from disk
			audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
			audioFormat = audioInputStream.getFormat();
			dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Play the audio
	 */
	public void play() {
		try {
			byte[] b = new byte[1024*5];
			int len = 0;
			sourceDataLine.open(audioFormat, 1024*5);
			sourceDataLine.start();
			audioInputStream.mark(12358946);
			while ((len = audioInputStream.read(b)) > 0) {
				sourceDataLine.write(b, 0, len);
			}
			sourceDataLine.drain();
			sourceDataLine.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
