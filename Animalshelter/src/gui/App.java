package gui;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

public class App {
	public static void main(String[] args) {
//		FlatMacLightLaf.setup();
//		FlatIntelliJLaf.setup();
		FlatLightLaf.setup();
		
        SwingUtilities.invokeLater(() -> {
        	new ShelterFrame();
        });
    }
}
