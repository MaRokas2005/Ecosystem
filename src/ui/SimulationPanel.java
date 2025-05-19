package ui;

import animal.*;
import exceptions.EcosystemException;
import exceptions.InvalidAttackException;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * JPanel subclass that handles the simulation of an ecosystem with rabbits and wolves.
 * <p>
 * This panel maintains and updates the state of animals, processes movements and interactions,
 * and handles user input such as selecting wolves and initiating attacks.
 * </p>
 * <p>
 * It also manages loading and saving of the simulation state to/from a file.
 * </p>
 *
 * <p>The simulation runs on a game loop timer, updating rabbits' random movements and wolves' pursuits.</p>
 *
 * @author Rokas Braidokas
 */
public class SimulationPanel extends JPanel implements MouseListener {

    private final Integer COOLDOWN_TIME = 3 * 60;
    private final ImageIcon rabbitIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/rabbit.png")));
    private final ImageIcon wolfIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/wolf.png")));
    private final List<Animal> animals = new ArrayList<>();
    private final Map<Rabbit, Integer> cooldown = new HashMap<>();
    private final Map<Wolf, Rabbit> pursuits = new HashMap<>();
    private final Map<Rabbit, Point> rabbitsTrajectory = new HashMap<>();
    private Wolf selectedWolf = null;
    private final Random rand = new Random();

    /**
     * Constructs the simulation panel, sets up the background color, mouse listener,
     * and starts the simulation game loop.
     */
    public SimulationPanel() {
        addMouseListener(this);
        setBackground(Color.decode("0x095c21"));
        startGameLoop();
    }

    /**
     * Starts the simulation's game loop using a Swing timer.
     * Updates game state and repaints at regular intervals (~40 FPS).
     */
    private void startGameLoop() {
        new Timer(25, _ -> {
            updateGameTasks();
            SwingUtilities.invokeLater(this::repaint);
        }).start();
    }

    /**
     * Updates the game state by performing rabbit movements and wolf pursuits.
     */
    private void updateGameTasks() {
        makeOneStepOfRabbitRunner();
        makeOneStepOfPursuits();
    }

    /**
     * Handles the logic for rabbits running randomly on the panel.
     * Includes cooldown timers and movement trajectory calculations.
     */
    private void makeOneStepOfRabbitRunner() {
        // ... implementation unchanged ...
    }

    /**
     * Handles the logic for wolves pursuing rabbits and attacking them if close enough.
     */
    private void makeOneStepOfPursuits() {
        // ... implementation unchanged ...
    }

    /**
     * Paints all animals onto the panel, including highlighting the selected wolf.
     *
     * @param g the Graphics context in which to paint
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ... implementation unchanged ...
    }

    /**
     * Handles mouse press events for selecting wolves or ordering a wolf to attack a rabbit.
     *
     * @param e the MouseEvent object containing details about the mouse press
     */
    public void mousePressed(MouseEvent e) {
        // ... implementation unchanged ...
    }

    /**
     * Adds a new wolf to the simulation at a random location within the panel bounds.
     */
    public void addWolf() {
        animals.add(new Wolf(40 + rand.nextInt(getWidth() - 80), 40 + rand.nextInt(getHeight() - 80)));
    }

    /**
     * Adds a new rabbit to the simulation at a random location within the panel bounds.
     */
    public void addRabbit() {
        animals.add(new Rabbit(40 + rand.nextInt(getWidth() - 80), 40 + rand.nextInt(getHeight() - 80)));
    }

    /**
     * Saves the current list of animals to a file named "animals.dat".
     * Rabbits are stopped before saving.
     * Runs in a separate thread to avoid blocking the UI.
     */
    public void saveAnimals() {
        // ... implementation unchanged ...
    }

    /**
     * Loads the list of animals from the file "animals.dat" and replaces the current simulation state.
     * Runs in a separate thread to avoid blocking the UI.
     */
    public void loadAnimals() {
        // ... implementation unchanged ...
    }

    // Empty implementations for MouseListener interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
