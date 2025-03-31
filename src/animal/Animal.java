package animal;

import interfaces.Movable;

public abstract class Animal implements Movable {
    private int x;
    private int y;
    private String name;
    private boolean isAlive;
    private static int quantity = 0;
    private static final int speed = 100;
    protected final double speedMultiplier;

    public Animal() {
        this(0, 0);
    }
    public Animal(int x, int y) {
        this(x, y, 1);
    }
    public Animal(int x, int y, double speedMultiplier) {
        this(x, y, speedMultiplier, "Animal " + quantity);
    }
    public Animal(int x, int y, double speedMultiplier, String name) {
        this.x = x;
        this.y = y;
        this.speedMultiplier = speedMultiplier;
        this.name = name;
        isAlive = true;
        quantity++;
    }
    public final int getX() {
        return x;
    }
    public final int getY() {
        return y;
    }
    public final int getSpeed() { return (int)(speed * speedMultiplier); }
    public static int getQuantity() {
        return quantity;
    }
    public final String getName() {
        return name;
    }
    public final boolean isAlive() {return isAlive;}
    public final void setX(int x) {
        this.x = x;
    }
    public final void setY(int y) {
        this.y = y;
    }
    public void setName(String name) {
        this.name = name;
    }
    public final void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    public final void move(int distance, double degrees) {
        x += (int) (Math.cos(Math.toRadians(degrees)) * distance);
        y += (int) (Math.sin(Math.toRadians(degrees)) * distance);
    }
    public void kill() {
        isAlive = false;
    }
    public String toString() {
        return "(name=" + name + ", x=" + x + ", y=" + y + ", speed=" + getSpeed()
                + ", isAlive=" + isAlive + ", quantity=" + quantity + ")";
    }
}