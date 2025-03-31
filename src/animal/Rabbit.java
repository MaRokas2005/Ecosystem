package animal;

import java.util.UUID;

public class Rabbit extends Animal {
    private static final double defaultSpeedMultiplier = 0.5d;
    private boolean isRunning = false;

    public Rabbit() {
        this(0, 0);
    }
    public Rabbit(int x, int y) {
        this(x, y, defaultSpeedMultiplier);
    }
    public Rabbit(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Rabbit " + UUID.randomUUID());
    }
    public Rabbit(int x, int y, double speedMultiplier, String name) {
        super(x,  y, speedMultiplier, name);
    }
    public void setName(String name) {super.setName("Rabbit " + name);}
    public void run() {
        isRunning = true;
    }
    public void stop() { isRunning = false; }
    public boolean isRunning() {
        return isRunning;
    }
    public String toString() {
        return getClass().getName() + "@(base: " + super.toString() + ", is running=" + isRunning + ")";
    }
}
