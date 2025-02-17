package gui.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManager implements GUIConstants {
	/**
	 * The various icon types used in the application.
	 * {@link #APP} {@link #BACK} {@link #ANIMAL} {@link #ROOMS} {@link #ADOPTION} {@link #PATRON} {@link #CARETAKER} {@link #VET}
	 * @see GUIConstants
	 */
	public enum Type {
		/**
		 * Default icon representing the app itself.
		 */
		APP,
		/**
		 * Back arrow icon.
		 */
		BACK,
		/**
		 * Icon representing animals.
		 */
		ANIMAL,
		/**
		 * Icon representing rooms.
		 */
		ROOMS,
		/**
		 * Icon representing adoptions.
		 */
		ADOPTION,
		/**
		 * Icon representing patrons.
		 */
		PATRON,
		/**
		 * Icon representing caretakers.
		 */
		CARETAKER,
		/**
		 * Icon representing vets.
		 */
		VET
	}
	
	/**
	 * The icon size, used to determine control states.
	 * Actual size are defined in GUIConstants.
	 * {@link #DEFAULT} {@link #SMALL} {@link #LARGE}
	 * @see GUIConstants
	 */
	public enum Size {
		/**
		 * The default icon size.
		 */
		DEFAULT,
		/**
		 * A slightly smaller icon size.
		 */
		SMALL,
		/**
		 * A slightly larger icon size.
		 */
		LARGE,
	}
	
	/**
	 * Returns an ImageIcon of the desired type and size.
	 * @param type
	 * @param size
	 * @return
	 * @see Type
	 * @see Size
	 */
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
			case BACK:
				animalIcon = new ImageIcon("resources/back.png");
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
