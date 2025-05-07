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
    public void attack(@NotNull Rabbit rabbit) throws InvalidAttackException {
        if (!rabbit.isAlive()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is already dead.", this, rabbit);
        }
        if (rabbit.isRunning()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is running away.", this, rabbit);
        }
        if(Math.sqrt((int)Math.pow(rabbit.getX() - getX(), 2) + Math.pow(rabbit.getY() - getY(), 2)) > getSpeed()) {
            throw new InvalidAttackException("Cannot attack: distance between Rabbit and Wolf is too big.", this, rabbit);
        }

        rabbit.kill();
        rabbitsEaten++;
    }
    public String toString() {
        return getClass().getName() + "@(base: " + super.toString() + ", rabbitsEaten=" + rabbitsEaten + ")";
    }
    public Wolf clone() {
        return (Wolf) super.clone();
    }
}