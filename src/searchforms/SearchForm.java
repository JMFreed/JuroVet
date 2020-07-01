package searchforms;

import javax.swing.*;
import javax.swing.border.Border;

import managers.ActionManager;
import main.MainFrame;

import java.awt.*;

public abstract class SearchForm extends JPanel {

	private MainFrame mainFrame;
    protected final JButton closeButton = new JButton("Close");
    protected final JButton searchButton = new JButton("Search");
    private final GridBagConstraints gc = new GridBagConstraints();

    SearchForm(MainFrame mainFrame, String name) {
    	this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
    	Border innerBorder = BorderFactory.createTitledBorder(name);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        getCloseButton().setAction(ActionManager.getInstance(mainFrame).getHideSearchFrameAction());
        
        getGC().weightx = 1;
        getGC().weighty = 1;

        getGC().gridx = 0;
        getGC().gridy = 10;
        getGC().anchor = GridBagConstraints.LINE_START;
        add(searchButton, getGC());

        getGC().gridx = 2;
        getGC().gridy = 10;
        getGC().anchor = GridBagConstraints.LINE_END;
        add(closeButton, getGC());
    }
    

    // ACCESSORS
    public MainFrame getMainFrame() { return this.mainFrame; }
    public JButton getCloseButton() { return this.closeButton; }
    public JButton getSearchButton() { return this.searchButton; }
    public GridBagConstraints getGC() { return this.gc; }
}
