package animal;

import java.util.UUID;

/**
 * Represents a {@code Rabbit} in the ecosystem.
 * <p>
 * A {@code Rabbit} is a type of {@link Animal} with a default speed multiplier
 * and additional behavior for running.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class Rabbit extends Animal {

    private static final double defaultSpeedMultiplier = 0.5d;
    private boolean isRunning = false;

    /**
     * Constructs a {@code Rabbit} at position (0,0) with the default speed multiplier.
     */
    public Rabbit() {
        this(0, 0);
    }

    /**
     * Constructs a {@code Rabbit} at the given position with the default speed multiplier.
     *
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public Rabbit(int x, int y) {
        this(x, y, defaultSpeedMultiplier);
    }

    /**
     * Constructs a {@code Rabbit} at the given position and with the specified speed multiplier.
     *
     * @param x               the X-coordinate
     * @param y               the Y-coordinate
     * @param speedMultiplier the movement speed multiplier
     */
    public Rabbit(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Rabbit " + UUID.randomUUID());
    }

    /**
     * Constructs a {@code Rabbit} with full customization.
     *
     * @param x               the X-coordinate
     * @param y               the Y-coordinate
     * @param speedMultiplier the movement speed multiplier
     * @param name            the name of the rabbit
     */
    public Rabbit(int x, int y, double speedMultiplier, String name) {
        super(x, y, speedMultiplier, name);
    }

    /**
     * Sets the name of the rabbit. This method prefixes the provided name with "Rabbit ".
     *
     * @param name the new name to assign (without the "Rabbit " prefix)
     */
    @Override
    public void setName(String name) {
        super.setName("Rabbit " + name);
    }

    /**
     * Sets the rabbit to a running state.
     */
    public void run() {
        isRunning = true;
    }

    /**
     * Stops the rabbit from running.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Checks whether the rabbit is currently running.
     *
     * @return {@code true} if the rabbit is running, {@code false} otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Returns a string representation of the rabbit, including base animal info and running status.
     *
     * @return a string describing the rabbit
     */
    @Override
    public String toString() {
        return getClass().getName() + "@(base: " + super.toString() + ", isRunning=" + isRunning + ")";
    }

    /**
     * Creates and returns a copy of this {@code Rabbit}.
     *
     * @return a cloned {@code Rabbit} instance
     */
    @Override
    public Rabbit clone() {
        return (Rabbit) super.clone();
    }
}
