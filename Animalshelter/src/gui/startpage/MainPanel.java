package gui.startpage;

import java.awt.BorderLayout;

import gui.ShelterPanel;

public class MainPanel extends ShelterPanel {
	public MainPanel() {
        StartpagePanel startPagePanel = new StartpagePanel();
        add(startPagePanel, BorderLayout.CENTER);
	}
}
