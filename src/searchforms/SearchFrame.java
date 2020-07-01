package searchforms;

import javax.swing.*;

import main.MainFrame;

import java.awt.*;

public class SearchFrame extends JFrame {
	
	private MainFrame mainFrame;
	private SearchForm searchForm;

    public SearchFrame(MainFrame mainFrame) {
        super("JuroVet");
        this.mainFrame = mainFrame;
        this.searchForm = null;
        setSize(new Dimension(300, 200));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false); 
        setResizable(false);
    }
    
    public SearchForm getSearchForm() {
    	return this.searchForm;
    }
    
    public void setSearchForm(SearchForm newSearchForm) {
    	if (getSearchForm() instanceof SearchForm) {
    		remove(getSearchForm());
    	}
    	this.searchForm = newSearchForm;
    	add(newSearchForm);
    	repaint();
    	setVisible(true);
    }
}