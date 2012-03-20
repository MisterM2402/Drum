import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {
	
	JPanel mainPanel;
    static ArrayList<JCheckBox> checkboxList;
    JFrame theFrame;
    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", 
    	       "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap", 
    	       "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", 
    	       "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", 
    	       "Open Hi Conga"};
	
	public GUI() {
		
		theFrame = new JFrame("Drum Machine");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        checkboxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);         
          
        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);
        
        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);
        
        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);
        
        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
           nameBox.add(new Label(instrumentNames[i]));
        }
        
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);
        theFrame.getContentPane().add(background);
          
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);
        for (int i = 0; i < 256; i++) {                    
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);            
        } // end loop
        DM.setUpMidi();
        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
    } // close method
		
	public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            DM.buildTrackAndStart();
        }
    } // close inner class
    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            DM.sequencer.stop();
        }
    } // close inner class
    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
     float tempoFactor = DM.sequencer.getTempoFactor(); 
            DM.sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
     } // close inner class
     public class MyDownTempoListener implements ActionListener {
         public void actionPerformed(ActionEvent a) {
     float tempoFactor = DM.sequencer.getTempoFactor();
            DM.sequencer.setTempoFactor((float)(tempoFactor * .97));
        }
    } // close inner class
	
}
