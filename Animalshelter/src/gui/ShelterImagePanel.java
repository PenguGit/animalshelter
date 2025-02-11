package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShelterImagePanel extends JPanel {
    private BufferedImage image;
    private int displayWidth;
    private int displayHeight;

    public ShelterImagePanel(byte[] imageData) {
    	setImageData(imageData);
    }

    public void setScale(double scale) {
        if (image != null) {
            displayWidth = (int) (image.getWidth() * scale);
            displayHeight = (int) (image.getHeight() * scale);
            setPreferredSize(new Dimension(displayWidth, displayHeight));
            revalidate(); // Important: forces the layout to recalculate
            repaint();
        }
    }
    
    public void setImageData(byte[] imageData) {
    	if (imageData != null) {
            try {
                image = ImageIO.read(new ByteArrayInputStream(imageData));
                if (image != null) {
                    displayWidth = image.getWidth();
                    displayHeight = image.getHeight();
                    setPreferredSize(new Dimension(displayWidth, displayHeight));
                }
                revalidate(); // Important: forces the layout to recalculate
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void clearImageData() {
    	setImageData(new byte[0]);
    }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, displayWidth, displayHeight, this);
		}
	}
}