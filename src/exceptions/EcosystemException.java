package exceptions;

/**
 * A custom exception class for handling errors specific to the ecosystem domain.
 * <p>
 * This exception can be used to represent application-specific issues that
 * are not covered by standard Java exceptions.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class EcosystemException extends Exception {

    /**
     * Constructs a new {@code EcosystemException} with the specified detail message.
     *
     * @param message the detail message that explains the reason for the exception
     */
    public EcosystemException(String message) {
        super(message);
    }
}
