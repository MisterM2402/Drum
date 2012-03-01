import java.io.*;
import javax.sound.sampled.*;
import javax.swing.Timer;
import java.awt.event.*;
   
public class PlaySound {
   
	static String folderPath = "/afs/inf.ed.ac.uk/user/s10/s1016269/workspace/DrumMachine/src/";
	//public static String fileName = "kick_01.wav";
	int c = 0;
	
   // Constructor
   public PlaySound(final int pattern[], final String fileName) {
	   Timer timer = new Timer(500, new ActionListener() { // 500 == 120bpm
		   public void actionPerformed(ActionEvent e) {
			   if (pattern[c] == 1) {
				   note(fileName); // Play audio file
			   }
			   c++;
			   if (c == pattern.length) {
				   c = 0; // Loop back to start of pattern
			   }
		   }
	   });
	   timer.start();
   }
   
   public static void note(String f) {
		   try {
		         // Open an audio input stream.
		    	 File soundFile = new File(folderPath + f);
		         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		         // Get a soundjava clip resource.
		         Clip clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
		         clip.start();
		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
   }
   
}
