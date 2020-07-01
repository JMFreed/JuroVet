package editforms;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.MainFrame;

public class EditForm extends JPanel {
	
	private EditFrame frame;
	private static final Dimension panelDim = new Dimension(600,400);
	private static final Dimension fieldDim = new Dimension(200,20);
	
	public EditForm(MainFrame mainFrame, EditFrame frame) {

        this.frame = frame;
        setLayout(new GridLayout(1, 1));
        setPreferredSize(new Dimension(750, 400));
        
        
	}

	public static Dimension getFieldDim() {
		return fieldDim;
	}

	public static Dimension getPanelDim() {
		return panelDim;
	}

	public EditFrame getFrame() {
		return this.frame;
	}

}
