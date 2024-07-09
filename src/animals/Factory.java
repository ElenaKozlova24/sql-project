package animals;

import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalType;

public class Factory {
    public static Animal create(AnimalType animalType) {
        switch (animalType) {
            case DOG:
                return new Dog();
            case CAT:
                return new Cat();
            case DUCK:
                return new Duck();
            default:
                return null;
        }
    }
}
