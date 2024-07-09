package animals.pets;

import animals.Animal;
import data.Color;

public class Cat extends Animal {

    void say() {
        System.out.println("мяу");
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
