package animal;

import exceptions.InvalidAttackException;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class Wolf extends Animal {
    private static final double defaultSpeedMultiplier = 1.2d;
    private int rabbitsEaten = 0;

    public Wolf() {
        this(0, 0);
    }
    public Wolf(int x, int y) {
        this(x, y, defaultSpeedMultiplier);
    }
    public Wolf(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Wolf " +  UUID.randomUUID());
    }
    public Wolf(int x, int y, double speedMultiplier, String name) {
        super(x, y, speedMultiplier, name);
    }
    public int getRabbitsEaten() {
        return rabbitsEaten;
    }
    public void setName(String name) {super.setName("Wolf " + name);}
    public boolean attack(@NotNull Rabbit rabbit) throws InvalidAttackException {
        if (!rabbit.isAlive()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is already dead. Name=" + rabbit.getName());
        }
        if (rabbit.isRunning()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is running away. Name=" + rabbit.getName());
        }

        rabbit.kill();
        rabbitsEaten++;
        return true;
    }
    public String toString() {
        return getClass().getName() + "@(base: " + super.toString() + ", rabbits eaten=" + rabbitsEaten + ")";
    }
}