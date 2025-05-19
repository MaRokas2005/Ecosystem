package animal.factory;

import animal.Animal;

/**
 * A factory interface for creating {@link Animal} instances.
 * <p>
 * Implementations of this interface should define how a specific type of
 * {@code Animal} is constructed.
 * </p>
 *
 * @author Rokas Braidokas
 */
public interface AnimalFactory {

    /**
     * Creates a new {@link Animal} instance.
     *
     * @return a new animal
     */
    Animal createAnimal();
}
