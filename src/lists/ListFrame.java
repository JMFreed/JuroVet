package lists;

import javax.swing.*;

import main.MainFrame;

import java.awt.*;

public class ListFrame extends JFrame {

    private List list;
    private MainFrame mainFrame;

    public ListFrame(List list, MainFrame mainFrame) {
        super("JuroVet");
        this.list = list;
        this.mainFrame = mainFrame;
        getList().getTableScrollPane().setPreferredSize(new Dimension(600,200));
        add(list, BorderLayout.CENTER);
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(
        		getMainFrame().getWidth()/2 - this.getWidth()/2, 
        		getMainFrame().getHeight()/2 - this.getHeight()/2);
        setVisible(true);
        setResizable(false);
    }

    public List getList() { return list; }
    public MainFrame getMainFrame() { return mainFrame; }
}
