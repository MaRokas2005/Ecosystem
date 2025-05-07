package animal.factory;

import animal.*;

public class RabbitFactory implements AnimalFactory {
    public Animal createAnimal() {
        return new Rabbit(0, 0, 0.8d);
    }
}