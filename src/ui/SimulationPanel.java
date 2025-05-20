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

    private final Integer COOLDOWN_TIME = (Integer) (3 * 60);
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
        for (Animal animal : animals) {
            if (animal instanceof Rabbit rabbit && rabbit.isAlive() && !rabbit.isRunning()) {
                if (cooldown.containsKey(rabbit)) {
                    if (cooldown.get(rabbit) > 0) {
                        cooldown.replace(rabbit, Integer.valueOf(cooldown.get(rabbit) - 1));
                        continue;
                    }
                    cooldown.remove(rabbit);
                }
                if (rand.nextInt(100) < 90) continue;
                int dx = rand.nextInt(4, 7) * 20 * (rand.nextBoolean() ? 1 : -1);
                int dy = rand.nextInt(4, 7) * 20 * (rand.nextBoolean() ? 1 : -1);;
                int nx = rabbit.getX() + dx;
                int ny = rabbit.getY() + dy;
                if (nx >= 40 && nx <= getWidth() - 40 && ny >= 40 && ny <= getHeight() - 40) {
                    rabbit.run();
                    rabbitsTrajectory.put(rabbit, new Point(nx, ny));
                }
            }
        }
        for (Iterator<Map.Entry<Rabbit, Point>> it = rabbitsTrajectory.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Rabbit, Point> entry = it.next();
            Rabbit rabbit = entry.getKey();
            Point point = entry.getValue();

            if (!rabbit.isAlive()) {
                it.remove();
                continue;
            }
            int dx = (int) point.getX() - rabbit.getX();
            int dy = (int) point.getY() - rabbit.getY();
            double dist = Math.hypot(dx, dy);
            if (dist < 5) {
                cooldown.put(rabbit, Integer.valueOf(rand.nextInt(COOLDOWN_TIME / 2, COOLDOWN_TIME)));
                rabbit.stop();
                it.remove();
                continue;
            }

            double angle = Math.toDegrees(Math.atan2(dy, dx));
            try {
                rabbit.move(1, angle + 30d);
                rabbit.move(2, angle);
            } catch (EcosystemException e) {
                SwingUtilities.invokeLater(() -> ErrorFrame.showErrorFrame("Error moving", e.getMessage(), this));
            }
        }
    }

    /**
     * Handles the logic for wolves pursuing rabbits and attacking them if close enough.
     */
    private void makeOneStepOfPursuits() {
        for (Iterator<Map.Entry<Wolf, Rabbit>> it = pursuits.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Wolf, Rabbit> entry = it.next();
            Wolf wolf = entry.getKey();
            Rabbit rabbit = entry.getValue();

            if (!rabbit.isAlive()) {
                it.remove();
                continue;
            }

            int dx = rabbit.getX() - wolf.getX();
            int dy = rabbit.getY() - wolf.getY();
            double dist = Math.hypot(dx, dy);

            if (dist < 20) {
                try {
                    wolf.attack(rabbit);
                } catch (InvalidAttackException e) {
                    SwingUtilities.invokeLater(() ->
                            ErrorFrame.showErrorFrame("Error attacking", e.getMessage(), this));
                }
                it.remove();
                continue;
            }

            double angle = Math.toDegrees(Math.atan2(dy, dx));
            try {
                wolf.move(10, angle);
            } catch (EcosystemException e) {
                SwingUtilities.invokeLater(() -> ErrorFrame.showErrorFrame("Error moving", e.getMessage(), this));
            }
        }
    }

    /**
     * Paints all animals onto the panel, including highlighting the selected wolf.
     *
     * @param g the Graphics context in which to paint
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Animal animal : animals) {
            if (!animal.isAlive()) continue;
            ImageIcon icon = null;
            try {
                if (animal instanceof Rabbit) {
                    icon = rabbitIcon;
                } else if (animal instanceof Wolf) {
                    icon = wolfIcon;
                }
            } catch (NullPointerException _) {}

            if (icon != null) {
                icon = new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
                int x = animal.getX() - icon.getIconWidth() / 2;
                int y = animal.getY() - icon.getIconHeight() / 2;
                icon.paintIcon(this, g, x, y);
            }
        }
        if (selectedWolf != null) {
            g.setColor(Color.RED);
            int x = selectedWolf.getX();
            int y = selectedWolf.getY();
            g.drawRect(x - 30, y - 30, 60, 60);
        }
    }

    /**
     * Handles mouse press events for selecting wolves or ordering a wolf to attack a rabbit.
     *
     * @param e the MouseEvent object containing details about the mouse press
     */
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed + " + e.getWhen());
        int mx = e.getX();
        int my = e.getY();
        Animal clicked = null;
        for (Animal a : animals) {
            if (!a.isAlive()) continue;
            int ax = a.getX();
            int ay = a.getY();
            if (Math.abs(mx - ax) < 30 && Math.abs(my - ay) < 30) {
                clicked = a;
                break;
            }
        }
        if (clicked instanceof Wolf) {
            selectedWolf = (Wolf) clicked;
            System.out.println("Wolf selected");
        } else if (clicked instanceof Rabbit rabbit && selectedWolf != null) {
            System.out.println("Sending wolf...");
            Wolf wolf = selectedWolf;
            selectedWolf = null;
            pursuits.put(wolf, rabbit);
        }
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
        new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("animals.dat"))) {
                animals.stream().filter(animal -> animal instanceof Rabbit).forEach(animal -> {((Rabbit) animal).stop();});
                oos.writeObject(new java.util.ArrayList<>(animals));
                //SuccessFrame.showSuccessFrame("Animals saved", "Animals saved successfully", this);
                System.out.println("Animals saved");
            } catch (IOException e) {
                ErrorFrame.showErrorFrame("Error saving animals", e.getMessage(), this);
                System.err.println("Failed to save animals: " + e.getMessage());
            }
        }).start();
    }

    /**
     * Loads the list of animals from the file "animals.dat" and replaces the current simulation state.
     * Runs in a separate thread to avoid blocking the UI.
     */
    public void loadAnimals() {
        new Thread(() -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("animals.dat"))) {
                List<Animal> loaded = (List<Animal>) ois.readObject();
                animals.clear();
                animals.addAll(loaded);
                selectedWolf = null;
                //SuccessFrame.showSuccessFrame("Animals loaded", "Animals loaded successfully", this);
                System.out.println("Animals loaded");
            } catch (IOException | ClassNotFoundException e) {
                ErrorFrame.showErrorFrame("Error loading animals", e.getMessage(), this);
                System.err.println("Failed to load animals: " + e.getMessage());
            }
        }).start();
    }

    // Empty implementations for MouseListener interface
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
