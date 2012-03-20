import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;

public class DM {
    
    static Sequencer sequencer;
    static Sequence sequence;
    static Track track;
    static int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    
    public static void main (String[] args) {
        new GUI();
    }
        
    public static void setUpMidi() {
    	try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
            
          } catch(Exception e) {e.printStackTrace();}
    } // close method
    
    public static void buildTrackAndStart() {
    	
        int[] trackList = null;
      
        sequence.deleteTrack(track);
        track = sequence.createTrack();
          for (int i = 0; i < 16; i++) {
            trackList = new int[16];
   
            int key = instruments[i];   
            for (int j = 0; j < 16; j++ ) {         
                
                JCheckBox jc = GUI.checkboxList.get(j + 16*i);
                if ( jc.isSelected()) {
                   trackList[j] = key;
                } else {
                   trackList[j] = 0;
                }                    
             } // close inner loop
     
        
             makeTracks(trackList);
             track.add(makeEvent(176,1,127,0,16));  
         } // close outer
         track.add(makeEvent(192,9,1,0,15));      
         try {
            sequencer.setSequence(sequence); 
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);                   
            sequencer.start();
            sequencer.setTempoInBPM(120);
         } catch(Exception e) {e.printStackTrace();}
      } // close buildTrackAndStart method
              
      public static void makeTracks(int[] list) {        
         
         for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
               track.add(makeEvent(144,9,key, 100, i));
               track.add(makeEvent(128,9,key, 100, i+1));
            }
         }
      }
          
      public static  MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
          MidiEvent event = null;
          try {
              ShortMessage a = new ShortMessage();
              a.setMessage(comd, chan, one, two);
              event = new MidiEvent(a, tick);
          } catch(Exception e) {e.printStackTrace(); }
          return event;
      }
  } // close class