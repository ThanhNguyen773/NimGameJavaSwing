package NIM;

import javax.swing.SwingUtilities;

public class Main {
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            StartMenu startMenu = new StartMenu();
	            startMenu.setVisible(true);
	        });
	    }

}
