package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class ShelterSearchField extends ShelterTextField {
	private Consumer<String> searchHandler;

    public ShelterSearchField(Consumer<String> searchHandler) {
        super();
    	this.searchHandler = searchHandler;
        init();
    }

    private void init() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (searchHandler != null) {
                    searchHandler.accept(getText());
                }
            }
        });
    }
}