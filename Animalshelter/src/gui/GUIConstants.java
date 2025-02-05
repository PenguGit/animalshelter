package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public interface GUIConstants {
	public static final String FONT_SANS = "Comic Sans MS";
	
	public static final String APP_TITEL = "AnimalShelter";
	public static final Rectangle FRAME_BOUNDS = new Rectangle(600, 100, 800, 300);

	public static final Color COLOR_PANEL = Color.WHITE;
	public static final Color COLOR_LABEL = Color.WHITE;

	public static final Font FONT_TAB = new Font(FONT_SANS, Font.PLAIN, 20);
	public static final Font FONT_TEXTFIELD = new Font(FONT_SANS, Font.PLAIN, 25);
	public static final Font FONT_LABELS = new Font(FONT_SANS, Font.PLAIN, 25);
	public static final Font FONT_BUTTON = new Font(FONT_SANS, Font.PLAIN, 30);
	public static final Font FONT_MESSAGES = new Font(FONT_SANS, Font.PLAIN, 20);
	public static final Font FONT_PANELTITEL = new Font(FONT_SANS, Font.PLAIN, 40);
}