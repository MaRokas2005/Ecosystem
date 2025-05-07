package ui;

import animal.*;
import exceptions.InvalidAttackException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationPanel extends JPanel implements MouseListener {
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private volatile Wolf selectedWolf = null;
    private final Random rand = new Random();

    public SimulationPanel() {
        addMouseListener(this);
        setBackground(Color.decode("0x095c21"));
        startRabbitRunner();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Animal animal : animals) {
            if (!animal.isAlive()) continue;
            if (animal instanceof Rabbit) g.setColor(Color.ORANGE);
            else if (animal instanceof Wolf) g.setColor(Color.GRAY);
            g.fillOval(animal.getX() - 10, animal.getY() - 10, 20, 20);
        }
        if (selectedWolf != null) {
            g.setColor(Color.RED);
            int x = selectedWolf.getX();
            int y = selectedWolf.getY();
            g.drawRect(x - 20, y - 20, 40, 40);
        }
    }

    public void mouseClicked(MouseEvent e) {
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
            repaint();
        } else if (clicked instanceof Rabbit && selectedWolf != null) {
            System.out.println("Sending wolf...");
            startPursuit(selectedWolf, (Rabbit) clicked);
            selectedWolf = null;
        }
    }

    private void startPursuit(Wolf wolf, Rabbit rabbit) {
        new Thread(() -> {
            try {
                while (rabbit.isAlive()) {
                    int dx = rabbit.getX() - wolf.getX();
                    int dy = rabbit.getY() - wolf.getY();
                    double dist = Math.hypot(dx, dy);
                    if (dist < 20) {
                        try{
                            wolf.attack(rabbit);
                        } catch (InvalidAttackException e) {
                            ErrorFrame.showErrorFrame("Error attacking", e.getMessage(), this);
                        }
                        SwingUtilities.invokeLater(this::repaint);
                        break;
                    }
                    double angle = Math.toDegrees(Math.atan2(dy, dx));
                    wolf.move(10, angle);
                    SwingUtilities.invokeLater(this::repaint);
                    Thread.sleep(30);
                }
            } catch (Exception ex) {
                System.err.println("error: " + ex.getMessage());
            }
        }).start();
    }

    public Runnable addWolf() {
        return () -> {
            animals.add(new Wolf(20 + rand.nextInt(getWidth() - 40), 20 + rand.nextInt(getHeight() - 40)));
            repaint();
        };
    }

    public Runnable addRabbit() {
        return () -> {
            animals.add(new Rabbit(20 + rand.nextInt(getWidth() - 40), 20 + rand.nextInt(getHeight() - 40)));
            repaint();
        };
    }

    public Runnable saveAnimals() {
        return () -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("animals.dat"))) {
                oos.writeObject(new java.util.ArrayList<>(animals));
            } catch (IOException e) {
                ErrorFrame.showErrorFrame("Error saving animals", e.getMessage(), this);
                System.err.println("Failed to save animals: " + e.getMessage());
            }
        };
    }

    public Runnable loadAnimals() {
        return () -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("animals.dat"))) {
                List<Animal> loaded = (List<Animal>) ois.readObject();
                animals.clear();
                animals.addAll(loaded);
                selectedWolf = null;
                SwingUtilities.invokeLater(this::repaint);
            } catch (IOException | ClassNotFoundException e) {
                ErrorFrame.showErrorFrame("Error loading animals", e.getMessage(), this);
                System.err.println("Failed to load animals: " + e.getMessage());
            }
        };
    }

    private void startRabbitRunner() {
        Timer rabbitTimer = new Timer(200, evt -> {
            for (Animal animal : animals) {
                if (animal instanceof Rabbit rabbit && rabbit.isAlive()) {
                    if (rand.nextBoolean()) continue;
                    int dx = rand.nextInt(11) - 5;
                    int dy = rand.nextInt(11) - 5;
                    int nx = rabbit.getX() + dx;
                    int ny = rabbit.getY() + dy;
                    if (nx >= 20 && nx <= getWidth() - 20 && ny >= 20 && ny <= getHeight() - 20) {
                        rabbit.move(dx, dy);
                    }
                }
            }
            repaint();
        });
        rabbitTimer.setCoalesce(false);
        rabbitTimer.start();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}