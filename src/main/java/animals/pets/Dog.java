package animals.pets;

import animals.Animal;
import data.Color;

public class Dog extends Animal {
    void say() {
        System.out.println("гав");
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public Object getAge() {
        return null;
    }

    @Override
    public Color getType() {
        return null;
    }
}

