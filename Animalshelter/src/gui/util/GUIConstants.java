package gui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

public interface GUIConstants {
	public static final String FONT_SANS = "Calibri";
	
	public static final String APP_TITEL = "Beim kleinen Dackel";
	
	public static final String SAVE_BUTTON = "Speichern";
	public static final String DELETE_BUTTON = "Löschen";
	public static final String NEW_BUTTON = "Neu";
	public static final String EDIT_BUTTON = "Bearbeiten";
	public static final String CANCEL_BUTTON = "Abbrechen";
	
	public static final Rectangle FRAME_BOUNDS = new Rectangle(600, 100, 800, 400);
	public static final String GREETING = "Wilkommen im Tierheim 'Beim kleinen Dackel'!";

	public static final Color COLOR_PANEL = Color.WHITE;
	public static final Color COLOR_LABEL = Color.WHITE;

	public static final Dimension DEFAULT_ICON_SIZE = new Dimension(64, 64);
	public static final Dimension SMALL_ICON_SIZE = new Dimension(48, 48);
	public static final Dimension LARGE_ICON_SIZE = new Dimension(96, 96);
	
	public static final Font FONT_TAB = new Font(FONT_SANS, Font.PLAIN, 20);
	public static final Font FONT_LIST = new Font(FONT_SANS, Font.PLAIN, 30);
	public static final Font FONT_TEXTFIELD = new Font(FONT_SANS, Font.PLAIN, 25);
	public static final Font FONT_LABELS = new Font(FONT_SANS, Font.PLAIN, 25);
	public static final Font FONT_BUTTON = new Font(FONT_SANS, Font.PLAIN, 30);
	public static final Font FONT_MESSAGES = new Font(FONT_SANS, Font.PLAIN, 20);
	public static final Font FONT_PANELTITEL = new Font(FONT_SANS, Font.PLAIN, 40);
}