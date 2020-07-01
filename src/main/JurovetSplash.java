package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JurovetSplash extends JFrame {
	
	private static JurovetSplash instance;
	
	/*
	 * Splash for JuroVet
	 * @params
	 * String filepath: the path to the image used for the splash
	 * int waitTime: how long the splash appears for
	 */

    protected JurovetSplash(String filepath, int waitTime) {
        super();
        setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon(filepath));
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = label.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2), 
        			screenSize.height/2 - (labelSize.height/2));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });

        final int pause = waitTime;
        final Runnable closerRunner = () -> {
            setVisible(false);
            dispose();
        };

        Runnable waitRunner = () -> {
            try {
                Thread.sleep(pause);
                SwingUtilities.invokeAndWait(closerRunner);
            }
            catch(Exception e) { e.printStackTrace(); }
        };

        setPreferredSize(labelSize);
        setVisible(true);
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
    
    public static JurovetSplash getInstance() {
    	if (instance == null) {
    		instance = new JurovetSplash("C:\\Users\\james.freed\\eclipse-workspace\\JuroVetX2\\src\\images\\splash.png", 3000);
    	}
    	return instance;
    }
    
}