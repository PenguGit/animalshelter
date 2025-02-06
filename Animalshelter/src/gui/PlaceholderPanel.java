package gui;

public class PlaceholderPanel extends ShelterPanel {
	public ShelterButton backButton;
	
	public PlaceholderPanel(String title) {
		ShelterLabel titleLabel = new ShelterLabel(title);
		add(titleLabel);
		backButton = new ShelterButton("<");
		add(backButton);
	}
}
