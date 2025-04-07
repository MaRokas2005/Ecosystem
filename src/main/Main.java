package main;
import animal.*;
import exceptions.AnimalException;
import exceptions.InvalidAttackException;
import interfaces.Movable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws AnimalException {
        Rabbit rab1 = new Rabbit(0, 5, .5, "trušis 1");
        Rabbit rab2 = new Rabbit(2, 9, .7, "trušis 2");
        Wolf wolf1 = new Wolf(9, 5, 1.4);
        Wolf wolf2 = new Wolf(-10, -80, 1.5);

        System.out.println("rab1: " + rab1);
        System.out.println("rab2: " + rab2);
        System.out.println("wolf1: " + wolf1);
        System.out.println("wolf2: " + wolf2 + "\n------------------------------------");

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(rab1);
        animals.add(rab2);
        animals.add(wolf1);
        animals.add(wolf2);

        try {
            wolf1.attack(rab1);
            wolf2.attack(rab1);
        } catch (InvalidAttackException e) {
            System.out.println("InvalidAtackException: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Unknown exception: " + e.getMessage());
        }

        try {
            for(Animal animal : animals) {
                animal.move((int)((Math.random()-.5)*100), 5d);
            }
        } catch (AnimalException e) {
            System.out.println("AnimalException: " + e.getMessage());
        } catch(Exception e){
            System.out.println("Unknown exception: " + e.getMessage());
        }
    }
}