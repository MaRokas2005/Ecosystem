package interfaces;

import exceptions.AnimalException;

public interface Movable extends Positionable {
    void move(int dx, int dy);
    void move(int distance, double degrees) throws AnimalException;
    int getSpeed();
}
