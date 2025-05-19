package main;

import ui.AnimalApp;

import javax.swing.*;

/**
 * The entry point of the application.
 * <p>
 * Initializes the UI by launching the {@link AnimalApp} on the Swing event dispatch thread.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class Main {

    /**
     * The main method that starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnimalApp::new);
    }
}
