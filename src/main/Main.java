package main;
import animal.*;
import interfaces.Movable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Animal rab1 = new Rabbit(0, 5, .5, "trušis 1");
        Animal rab2 = new Rabbit(2, 9, .7, "trušis 2");
        Animal wolf1 = new Wolf(9, 5, 1.3995);
        Animal wolf2 = new Wolf(-10, -80, 1.5);
        Animal wolf3 = new Wolf(-10, -80);

        System.out.println("rab1: " + rab1);
        System.out.println("rab2: " + rab2);
        System.out.println("wolf1: " + wolf1);
        System.out.println("wolf2: " + wolf2);
        System.out.println("wolf3: " + wolf3 + "\n------------------------------------");

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(rab1);
        animals.add(rab2);
        animals.add(wolf1);
        animals.add(wolf2);
        animals.add(wolf3);

        for(Animal animal : animals) {
            moveObject(animal, (int)(Math.random()*10), (int)(Math.random()*15));
        }
        System.out.println("Panaudojus interface'ą...");
        System.out.println("------------------------------------");
        animals.stream().map(Animal::toString).forEach(System.out::println);
    }
    private static void moveObject(@NotNull Movable object, int dx, int dy) {
        object.move(dx, dy);
    }
}