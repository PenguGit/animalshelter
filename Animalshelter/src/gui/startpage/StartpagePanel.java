package gui.startpage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import gui.ShelterButton;
import gui.ShelterLabel;
import gui.ShelterPanel;

public class StartpagePanel extends ShelterPanel {

	public StartpagePanel() {
		super();
		setLayout(new BorderLayout());
        ShelterLabel titleLabel = new ShelterLabel("Welcome to the Start Panel");
        add(titleLabel, BorderLayout.NORTH);
        
        ShelterPanel buttonPanel = new ShelterPanel();
        buttonPanel.setLayout(new GridBagLayout());  // 2x3 grid, spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (int i = 1; i <= 6; i++) {
            ShelterButton button = new ShelterButton("Button " + i);
            button.setPreferredSize(new Dimension(200, 200));

            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.insets = new java.awt.Insets(30, 30, 30, 30);

            buttonPanel.add(button, gbc);
            
            // Move to the next position in the grid
            if (i % 3 == 0) {
                gbc.gridx = 0; // Start at the first column
                gbc.gridy++;  // Move to the next row
            } else {
                gbc.gridx++; // Move to the next column
            }
        }
        add(buttonPanel, BorderLayout.CENTER);
	}
}
