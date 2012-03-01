public class DM {
	
	public static void main(String[] args) {
		
		int p[] = {1,0,0,0,1,0,0,0}; // Pattern to play; 1 = ON, 0 = OFF
		int p1[] = {0,0,1,0,0,0,1,0,0};
		int p2[] = {1,1,1,1,1,1,1,1};
		
		new GUI();
	    new PlaySound(p, "kick_01.wav"); // Will be moved to fire on button press
	    new PlaySound(p1, "snare02.wav");
	    new PlaySound(p2, "H_hat_01.wav");
		
	}
	
}
