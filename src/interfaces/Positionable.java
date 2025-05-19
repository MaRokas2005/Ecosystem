package interfaces;

/**
 * Represents an entity with a position in a 2D coordinate system.
 *
 * @author Rokas Braidokas
 */
public interface Positionable {

    /**
     * Returns the X-coordinate of the entity.
     *
     * @return the current X-coordinate
     */
    int getX();

    /**
     * Returns the Y-coordinate of the entity.
     *
     * @return the current Y-coordinate
     */
    int getY();

    /**
     * Sets the X-coordinate of the entity.
     *
     * @param x the new X-coordinate
     */
    void setX(int x);

    /**
     * Sets the Y-coordinate of the entity.
     *
     * @param y the new Y-coordinate
     */
    void setY(int y);
}
