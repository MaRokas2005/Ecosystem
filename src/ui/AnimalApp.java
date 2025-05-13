package ui;

import javax.swing.*;
import java.awt.*;

public class AnimalApp {
    private final SimulationPanel simulationPanel = new SimulationPanel();

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
