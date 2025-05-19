package exceptions;

import animal.Rabbit;
import animal.Wolf;

/**
 * Exception thrown when an invalid attack occurs between a {@link Wolf} and a {@link Rabbit}
 * in the ecosystem simulation.
 * <p>
 * This exception is typically used to signal that a {@code Wolf} attempted to attack a {@code Rabbit}
 * under invalid conditions, such as being too far away.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class InvalidAttackException extends EcosystemException {

    private Wolf wolf;
    private Rabbit rabbit;

    /**
     * Constructs a new {@code InvalidAttackException} with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidAttackException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code InvalidAttackException} with a message and the involved {@code Wolf} and {@code Rabbit}.
     *
     * @param message the detail message
     * @param wolf the {@code Wolf} involved in the invalid attack
     * @param rabbit the {@code Rabbit} that was the target of the invalid attack
     */
    public InvalidAttackException(String message, Wolf wolf, Rabbit rabbit) {
        super(message);
        this.wolf = wolf;
        this.rabbit = rabbit;
    }

    /**
     * Returns the {@code Wolf} involved in the invalid attack.
     *
     * @return the {@code Wolf} instance
     */
    public Wolf getWolf() {
        return wolf;
    }

    /**
     * Returns the {@code Rabbit} involved in the invalid attack.
     *
     * @return the {@code Rabbit} instance
     */
    public Rabbit getRabbit() {
        return rabbit;
    }

    /**
     * Calculates and returns the distance between the {@code Wolf} and the {@code Rabbit}
     * involved in the invalid attack.
     * <p>
     * The distance is calculated using the Euclidean formula and cast to an integer.
     * </p>
     *
     * @return the distance between the {@code Wolf} and the {@code Rabbit}
     */
    public int getDistance() {
        return (int) Math.sqrt(
                (int) Math.pow(rabbit.getX() - wolf.getX(), 2) +
                        (int) Math.pow(rabbit.getY() - wolf.getY(), 2)
        );
    }
}
