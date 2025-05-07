package main;
import exceptions.EcosystemException;
import ui.AnimalApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws EcosystemException {
        SwingUtilities.invokeLater(AnimalApp::new);
    }
}