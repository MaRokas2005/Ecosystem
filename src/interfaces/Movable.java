package interfaces;

import exceptions.EcosystemException;

public interface Movable extends Positionable {
    void move(int dx, int dy);
    void move(int distance, double degrees) throws EcosystemException;
    int getSpeed();
}
