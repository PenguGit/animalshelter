package gui.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManager implements GUIConstants {
	public enum Type {
		APP,
		ANIMAL,
		ROOMS,
		ADOPTION,
		PATRON,
		CARETAKER,
		VET
	}
	
	public enum Size {
		DEFAULT,
		SMALL,
		LARGE,
	}
	
	public static ImageIcon getIcon(Type type, Size size) {
		ImageIcon animalIcon;
		switch(type) {
			case ADOPTION:
				animalIcon = new ImageIcon("resources/adoption.png");
				break;
			case ANIMAL:
				animalIcon = new ImageIcon("resources/animal.png");
				break;
			case APP:
				animalIcon = new ImageIcon("resources/dachshund.png");
				break;
			case CARETAKER:
				animalIcon = new ImageIcon("resources/caretaker.png");
				break;
			case PATRON:
				animalIcon = new ImageIcon("resources/patron.png");
				break;
			case ROOMS:
				animalIcon = new ImageIcon("resources/rooms.png");
				break;
			case VET:
				animalIcon = new ImageIcon("resources/vet.png");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		switch(size) {
			case DEFAULT:
				return new ImageIcon(animalIcon.getImage().getScaledInstance(DEFAULT_ICON_SIZE.width, DEFAULT_ICON_SIZE.height, Image.SCALE_SMOOTH));
			case SMALL:
				return new ImageIcon(animalIcon.getImage().getScaledInstance(SMALL_ICON_SIZE.width, SMALL_ICON_SIZE.height, Image.SCALE_SMOOTH));
			case LARGE:
				return new ImageIcon(animalIcon.getImage().getScaledInstance(LARGE_ICON_SIZE.width, LARGE_ICON_SIZE.height, Image.SCALE_SMOOTH));
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
}
