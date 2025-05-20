package animal.factory;

import animal.*;

/**
 * A factory for creating {@link Wolf} instances.
 * <p>
 * This implementation of {@link AnimalFactory} creates a {@code Wolf}
 * positioned at (0, 0) with a speed multiplier of 1.2.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class WolfFactory implements AnimalFactory {
    /**
     * Constructs a new {@code WolfFactory}.
     * <p>
     * This constructor is public to allow instantiation of the factory
     * for creating {@link Wolf} objects.
     */
    public WolfFactory() {}
    /**
     * Creates a new {@link Wolf} instance at position (0, 0)
     * with a speed multiplier of 1.2.
     *
     * @return a new {@code Wolf}
     */
    @Override
    public Animal createAnimal() {
        return new Wolf(0, 0, 1.2d);
    }
}
