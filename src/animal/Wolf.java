package animal;

import exceptions.InvalidAttackException;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

/**
 * Represents a {@code Wolf} in the ecosystem.
 * <p>
 * A {@code Wolf} is a type of {@link Animal} that can attack {@link Rabbit} instances under
 * specific conditions. It keeps track of how many rabbits it has eaten.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class Wolf extends Animal {

    private static final double defaultSpeedMultiplier = 1.2d;
    private int rabbitsEaten = 0;

    /**
     * Constructs a {@code Wolf} at position (0,0) with the default speed multiplier.
     */
    public Wolf() {
        this(0, 0);
    }

    /**
     * Constructs a {@code Wolf} at the given position with the default speed multiplier.
     *
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public Wolf(int x, int y) {
        this(x, y, defaultSpeedMultiplier);
    }

    /**
     * Constructs a {@code Wolf} at the given position and with a specified speed multiplier.
     *
     * @param x               the X-coordinate
     * @param y               the Y-coordinate
     * @param speedMultiplier the movement speed multiplier
     */
    public Wolf(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Wolf " + UUID.randomUUID());
    }

    /**
     * Constructs a {@code Wolf} with full customization.
     *
     * @param x               the X-coordinate
     * @param y               the Y-coordinate
     * @param speedMultiplier the movement speed multiplier
     * @param name            the name of the wolf
     */
    public Wolf(int x, int y, double speedMultiplier, String name) {
        super(x, y, speedMultiplier, name);
    }

    /**
     * Returns the number of rabbits this wolf has eaten.
     *
     * @return the number of rabbits eaten
     */
    public int getRabbitsEaten() {
        return rabbitsEaten;
    }

    /**
     * Sets the name of the wolf. This method prefixes the provided name with "Wolf ".
     *
     * @param name the new name to assign (without the "Wolf " prefix)
     */
    @Override
    public void setName(String name) {
        super.setName("Wolf " + name);
    }

    /**
     * Attempts to attack a specified {@link Rabbit}.
     * <p>
     * The attack will fail and throw an {@link InvalidAttackException} if any of the following conditions are true:
     * <ul>
     *     <li>The rabbit is already dead</li>
     *     <li>The rabbit is running</li>
     *     <li>The rabbit is too far away (farther than the wolf's speed)</li>
     * </ul>
     * If the attack is successful, the rabbit is killed and the wolf's {@code rabbitsEaten} count is incremented.
     *
     * @param rabbit the rabbit to attack (must not be {@code null})
     * @throws InvalidAttackException if the attack is not permitted
     */
    public void attack(@NotNull Rabbit rabbit) throws InvalidAttackException {
        if (!rabbit.isAlive()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is already dead.", this, rabbit);
        }
        if (rabbit.isRunning()) {
            throw new InvalidAttackException("Cannot attack: Rabbit is running away.", this, rabbit);
        }
        if (Math.sqrt(Math.pow(rabbit.getX() - getX(), 2) + Math.pow(rabbit.getY() - getY(), 2)) > getSpeed()) {
            throw new InvalidAttackException("Cannot attack: distance between Rabbit and Wolf is too big.", this, rabbit);
        }

        rabbit.kill();
        rabbitsEaten++;
    }

    /**
     * Returns a string representation of the wolf, including base animal info and rabbits eaten.
     *
     * @return a string describing the wolf
     */
    @Override
    public String toString() {
        return getClass().getName() + "@(base: " + super.toString() + ", rabbitsEaten=" + rabbitsEaten + ")";
    }

    /**
     * Creates and returns a copy of this {@code Wolf}.
     *
     * @return a cloned {@code Wolf} instance
     */
    @Override
    public Wolf clone() {
        return (Wolf) super.clone();
    }
}
