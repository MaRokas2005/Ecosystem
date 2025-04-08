package exceptions;

import animal.Rabbit;
import animal.Wolf;

public class InvalidAttackException extends EcosystemException {
    Wolf wolf;
    Rabbit rabbit;
    public InvalidAttackException(String message) {
        super(message);
    }
    public InvalidAttackException(String message, Wolf wolf, Rabbit rabbit) {
        super(message);
        this.wolf = wolf;
        this.rabbit = rabbit;
    }
    public Wolf getWolf() { return wolf; }
    public Rabbit getRabbit() { return rabbit; }
    public int getDistance() { return (int)Math.sqrt((int)Math.pow(rabbit.getX() - wolf.getX(), 2) + (int)Math.pow(rabbit.getY() - wolf.getY(), 2)); }
}
