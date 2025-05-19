package animal.factory;

import animal.*;

/**
 * A factory for creating {@link Rabbit} instances.
 * <p>
 * This implementation of {@link AnimalFactory} creates a {@code Rabbit}
 * positioned at (0, 0) with a speed multiplier of 0.8.
 * </p>
 *
 * @author Rokas Braidokas
 */
public class RabbitFactory implements AnimalFactory {

    /**
     * Creates a new {@link Rabbit} instance at position (0, 0)
     * with a speed multiplier of 0.8.
     *
     * @return a new {@code Rabbit}
     */
    @Override
    public Animal createAnimal() {
        return new Rabbit(0, 0, 0.8d);
    }
}
