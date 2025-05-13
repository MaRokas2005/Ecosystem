package main;
import ui.AnimalApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnimalApp::new);
    }
}