package editforms;

import javax.swing.*;

import main.MainFrame;
import searchforms.SearchForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class EditFrame extends JFrame {
	
	private EditForm editForm;

    // CONSTRUCTOR
    public EditFrame(MainFrame mainFrame) {
        super("JuroVet: Edit Account");
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);
        setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints mainGC = new GridBagConstraints();

        JButton saveButton = new JButton("Save");

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            editForm.getFrame().setVisible(false);
        });

        mainGC.weightx = 0.01;
        mainGC.weighty = 0.01;

        mainGC.gridx = 0;
        mainGC.gridy = 10;
        mainGC.fill = GridBagConstraints.NONE;
        mainGC.anchor = GridBagConstraints.LINE_START;
        add(saveButton, mainGC);

        mainGC.gridx = 0;
        mainGC.gridy = 10;
        mainGC.fill = GridBagConstraints.NONE;
        mainGC.anchor = GridBagConstraints.LINE_END;
        add(closeButton, mainGC);
    }
    
    public EditForm getEditForm() {
    	return this.editForm;
    }
    
    public void setEditForm(EditForm newEditForm) {
    	if (getEditForm() instanceof EditForm) {
    		remove(getEditForm());
    	}
    	this.editForm = newEditForm;
    	add(newEditForm);
    	repaint();
    	setVisible(true);
    }
}
