public class Animal {
    private int x;
    private int y;
    private static int quantity = 0;
    private static final int speed = 100;
    private final double speedMultiplier;

//    private static final Cleaner cleaner = Cleaner.create();
//    private final Cleaner.Cleanable cleanable;
//    private static class State implements Runnable {
//        public void run() {
//            --quantity;
//        }
//        cleanable = cleaner.register(this, new State()); // {put in constructor}
//    }

    public Animal() {
        this(0, 0, 1);
    }
    public Animal(int x, int y) {
        this(x, y, 1);
    }
    public Animal(int x, int y, double speedMultiplier) {
        this.x = x;
        this.y = y;
        this.speedMultiplier = speedMultiplier;
        quantity++;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSpeed() { return (int)(speed * speedMultiplier); }
    public static int getQuantity() {
        return quantity;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    public void move(int distance, double degrees) {
        x += (int) (Math.cos(Math.toRadians(degrees)) * distance);
        y += (int) (Math.sin(Math.toRadians(degrees)) * distance);
    }
    public String toString() {
        return "(x=" + x + ", y=" + y + ", speed=" + getSpeed() + ", quantity=" + quantity + ")";
    }
    public void println() {
        System.out.println(this);
    }
}