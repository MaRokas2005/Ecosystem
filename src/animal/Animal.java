package animal;

import exceptions.EcosystemException;
import interfaces.Movable;

import java.io.Serializable;

/**
 * An abstract representation of an animal in the ecosystem.
 * <p>
 * This class provides common properties and behaviors for all animals, including
 * position, movement, speed, life status, and naming. It also supports cloning and serialization.
 * </p>
 *
 * @author Rokas Braidokas
 */
public abstract class Animal implements Movable, Cloneable, Serializable {

    private int x;
    private int y;
    private String name;
    private boolean isAlive;
    private static int quantity = 0;
    private static final int speed = 100;
    /**
     * Multiplier applied to the base speed of an animal to adjust its movement rate.
     * A value greater than 1.0 increases the speed, while a value less than 1.0 decreases it.
     * Default is 1.0
     */
    protected final double speedMultiplier;

    /**
     * Constructs a default {@code Animal} at position (0,0) with default speed multiplier.
     */
    public Animal() {
        this(0, 0);
    }

    /**
     * Constructs an {@code Animal} at the specified position with default speed multiplier.
     *
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public Animal(int x, int y) {
        this(x, y, 1);
    }

    /**
     * Constructs an {@code Animal} at the specified position with a given speed multiplier.
     *
     * @param x              the X-coordinate
     * @param y              the Y-coordinate
     * @param speedMultiplier the factor by which the base speed is multiplied
     */
    public Animal(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Animal " + quantity);
    }

    /**
     * Constructs an {@code Animal} with full customization.
     *
     * @param x               the X-coordinate
     * @param y               the Y-coordinate
     * @param speedMultiplier the factor by which the base speed is multiplied
     * @param name            the name of the animal
     */
    public Animal(int x, int y, double speedMultiplier, String name) {
        this.x = x;
        this.y = y;
        this.speedMultiplier = speedMultiplier;
        this.name = name;
        this.isAlive = true;
        quantity++;
    }

    /**
     * Returns the current X-coordinate of the animal.
     *
     * @return the X-coordinate
     */
    public final int getX() {
        return x;
    }

    /**
     * Returns the current Y-coordinate of the animal.
     *
     * @return the Y-coordinate
     */
    public final int getY() {
        return y;
    }

    /**
     * Returns the effective speed of the animal after applying the speed multiplier.
     *
     * @return the speed as an integer
     */
    public final int getSpeed() {
        return (int)(speed * speedMultiplier);
    }

    /**
     * Returns the total number of animal instances created.
     *
     * @return the total quantity
     */
    public static int getQuantity() {
        return quantity;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Checks whether the animal is alive.
     *
     * @return {@code true} if alive; {@code false} otherwise
     */
    public final boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets the X-coordinate of the animal.
     *
     * @param x the new X-coordinate
     */
    public final void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y-coordinate of the animal.
     *
     * @param y the new Y-coordinate
     */
    public final void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Moves the animal by the given offset in X and Y directions.
     *
     * @param dx delta X
     * @param dy delta Y
     */
    public final void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    /**
     * Moves the animal in a specified direction and distance using polar coordinates.
     *
     * @param distance the distance to move (must be non-negative)
     * @param degrees  the angle in degrees from the X-axis
     * @throws EcosystemException if distance is negative
     */
    public final void move(int distance, double degrees) throws EcosystemException {
        if (distance < 0)
            throw new EcosystemException("Invalid distance. Less than zero. distance = " + distance);
        x += (int) (Math.cos(Math.toRadians(degrees)) * distance);
        y += (int) (Math.sin(Math.toRadians(degrees)) * distance);
    }

    /**
     * Marks the animal as no longer alive.
     */
    public void kill() {
        isAlive = false;
    }

    /**
     * Returns a string representation of the animal, including its name, position, speed, and life status.
     *
     * @return a string describing the animal
     */
    @Override
    public String toString() {
        return "(name=" + name + ", x=" + x + ", y=" + y + ", speed=" + getSpeed()
                + ", isAlive=" + isAlive + ", quantity=" + quantity + ")";
    }

    /**
     * Creates and returns a copy of this animal.
     *
     * @return a cloned {@code Animal} instance
     * @throws AssertionError if cloning is not supported (should never happen)
     */
    @Override
    public Animal clone() {
        try {
            quantity++;
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen since we implement Cloneable
        }
    }
}
