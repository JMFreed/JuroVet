package main;

import java.io.IOException;

import javax.swing.*;

public class Main {
	
	/*
	 * Runs the JuroVet program
	 * Creates instance of MainFrame
	 * Splash appears for 3 seconds, then disappears
	 */

    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
        	   MainFrame.getInstance();
        	   JurovetSplash.getInstance();
           }
       });
    }
}