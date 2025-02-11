package gui;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

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
