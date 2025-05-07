package animal.factory;

import animal.*;

public class WolfFactory implements AnimalFactory {
    public Animal createAnimal() {
        return new Wolf(0, 0, 1.2d);
    }
}
