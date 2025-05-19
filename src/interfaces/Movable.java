package interfaces;

import exceptions.EcosystemException;

/**
 * Represents entities that can move within a 2D coordinate system.
 * <p>
 * Extends {@link Positionable} to include movement capabilities.
 * </p>
 *
 * @author Rokas Braidokas
 */
public interface Movable extends Positionable {

    /**
     * Moves the entity by the specified delta values along the X and Y axes.
     *
     * @param dx the change in X-coordinate
     * @param dy the change in Y-coordinate
     */
    void move(int dx, int dy);

    /**
     * Moves the entity by a certain distance in a specified direction.
     * <p>
     * The direction is given in degrees where 0 degrees points to the right (positive X axis)
     * and increases counterclockwise.
     * </p>
     *
     * @param distance the distance to move (must be non-negative)
     * @param degrees  the direction to move in degrees
     * @throws EcosystemException if the distance is negative or movement fails
     */
    void move(int distance, double degrees) throws EcosystemException;

    /**
     * Returns the movement speed of the entity.
     *
     * @return the speed value
     */
    int getSpeed();
}
