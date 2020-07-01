package searchforms;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.UIManager;

/* TextFieldHighlighter
// Highlights a JTextField when clicked on by the user
// Unhighlighted when the JTextField is no longer in focus
*/

public class TextFieldHighlighter {
	
	FocusListener highlighter;

    public TextFieldHighlighter(JTextField field) {

        this.highlighter = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                e.getComponent().setBackground(Color.CYAN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                e.getComponent().setBackground(UIManager.getColor("TextField.background"));
            }
        };

        field.addFocusListener(highlighter);
    }
    
    public FocusListener getHighlighter() {
    	return highlighter;
    }
}