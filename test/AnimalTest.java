import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    Animal animalTest;
    @Test
    public void testConstructorWithNoArgConstructor() {
        animalTest = new Animal();
        Assertions.assertTrue(animalTest.getX() == 0 && animalTest.getY() == 0);
        animalTest = null;
        System.gc();
    }
    @Test
    public void testConstructorWithArgConstructor() {
        animalTest = new Animal(5, 9, 0.8);
        Assertions.assertTrue(animalTest.getX() == 5 && animalTest.getY() == 9);
        animalTest = null;
    }
    @Test
    public void testSetX() {
        animalTest = new Animal(5, 9, 1.2);
        animalTest.setX(18);
        Assertions.assertEquals(18, animalTest.getX());
        animalTest = null;
    }
    @Test
    public void testSetY() {
        animalTest = new Animal(5, 9, 0.8);
        animalTest.setY(29);
        Assertions.assertEquals(29, animalTest.getY());
        animalTest = null;
    }
//    @Test
//    public void testGetQuantity() {
//        animalTest = new Animal(5, 9);
//        System.out.println(animalTest.getQuantity());
//        Assertions.assertEquals(1, Animal.getQuantity());
//        animalTest = null;
//    }
    @Test
    public void testGetSpeed() {
        animalTest = new Animal(0, 0, 1);
        Assertions.assertEquals(100, animalTest.getSpeed());
        animalTest = null;
    }
    @Test
    public void testMoveWithDxAndDy() {
        animalTest = new Animal(5, 9, 0.7);
        animalTest.move(8, -4);
        Assertions.assertTrue(animalTest.getX() == 13 && animalTest.getY() == 5);
        animalTest = null;
    }
    @Test
    public void testMoveWithDistanceAndDegree() {
        animalTest = new Animal(0, 0, 2);
        animalTest.move(10, 90d);
        Assertions.assertTrue(animalTest.getX() == 0 && animalTest.getY() == 10);
        animalTest = null;
    }
}
