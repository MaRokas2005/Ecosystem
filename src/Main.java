import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gyvūnų skaičius = " + Animal.getQuantity());
        ArrayList<Animal> animals = new ArrayList<>();

        // Vienas gyvūnas
        Animal animal1 = new Animal();
        animals.add(animal1);
        System.out.println("Vienas gyvūnas.");
        System.out.println(animals + "\n\n");

        // Du gyvūnai
        Animal animal2 = new Animal(1, 9, 0.5d);
        animals.add(animal2);
        System.out.println("Du gyvūnai.");
        System.out.println(animals + "\n\n");

        // Trys gyvūnai
        Animal animal3 = new Animal(3, 8, 0.64d);
        animals.add(animal3);
        System.out.println("Trys gyvūnai.");
        System.out.println(animals + "\n\n");

        // Keturi gyvūnai
        Animal animal4 = new Animal(4, 7, 0.7d);
        animals.add(animal4);
        System.out.println("Keturi gyvūnai.");
        System.out.println(animals + "\n\n");

        // Testuojamas move(int dx, int dy)
        System.out.println("Testuojamas move(int dx, int dy) metodas.");
        Animal animal5 = new Animal(5, 8);
        animals.add(animal5);
        animal5.println();
        animal5.move(1, -5);
        System.out.println("animal5.move(1, -5)");
        animal5.println();

        // Testtuojamas move(int distance, double degrees)
        System.out.println("\n\nTestuojamas move(int distance, double degrees) metodas.");
        Animal animal6 = new Animal();
        animals.add(animal6);
        animal6.println();
        animal6.move(10, 90d);
        System.out.println("animal6.move(10, 90d)");
        animal6.println();

        animal6.setX(50);
        animal6.println();

        // išvedama gyvūnų kiekis
        System.out.println("\n\nkiekis = " + Animal.getQuantity());
        System.out.println(animals);

//        System.out.println("animal kiekis = " + Animal.getQuantity() + ", dog kiekis = " + Dog.getQuantity());
//        Dog dog = new Dog();
//        animals.add(dog);
//        System.out.println(animals);
//        System.out.println("animal kiekis = " + Animal.getQuantity() + ", dog kiekis = " + Dog.getQuantity());
    }
}