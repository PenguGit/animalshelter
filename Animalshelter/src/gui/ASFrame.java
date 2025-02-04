package gui;

import javax.swing.JFrame;

import data.DataManager;
import data.entities.Vet;

public class ASFrame extends JFrame {

	public static void main(String[] args) {
		Vet vet = new Vet("Reher", "Simon", "080069420", "nunya@business.com");
		
		DataManager.getInstance().saveEntity(vet);
	}

}
