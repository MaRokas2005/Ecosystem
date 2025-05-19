package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window for the Ecosystem Simulator.
 * <p>
 * Sets up the JFrame containing the simulation panel and control buttons.
 * </p>
 *
 * <p>
 * Provides buttons to add rabbits and wolves, and to save/load the simulation state.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class AnimalApp {

    private final SimulationPanel simulationPanel = new SimulationPanel();

    /**
     * Constructs the main application window, initializing the UI components.
     */
    public AnimalApp() {
        JFrame frame = new JFrame("Ecosystem Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setMinimumSize(new Dimension(800, 600));

        JPanel buttonPanel = getJPanel();

        frame.add(simulationPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        //SwingUtilities.invokeLater(simulationPanel::startSimulation);
    }

    /**
     * Creates the panel containing control buttons.
     * <p>
     * Buttons include adding rabbits, adding wolves, saving, and loading simulation state.
     * </p>
     *
     * @return a JPanel with the control buttons
     */
    private JPanel getJPanel() {
        JPanel buttonPanel = new JPanel();
        JButton addRabbit = new JButton("Add Rabbit");
        JButton addWolf = new JButton("Add Wolf");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        addWolf.addActionListener(_ -> simulationPanel.addWolf());
        addRabbit.addActionListener(_ -> simulationPanel.addRabbit());
        saveButton.addActionListener(_ -> simulationPanel.saveAnimals());
        loadButton.addActionListener(_ -> simulationPanel.loadAnimals());

        buttonPanel.add(addRabbit);
        buttonPanel.add(addWolf);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        return buttonPanel;
    }
}
