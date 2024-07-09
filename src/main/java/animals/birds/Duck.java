package animals.birds;

import animals.Animal;
import data.AnimalType;
import data.Color;

public class Duck extends Animal implements animals.birds.Flying {
    public Duck(String маша, int i, float v, Color gray, AnimalType cat) {
        super(маша, i, v, gray, cat);
    }

    public Duck() {
        super();
    }

    void say() {
        System.out.println("кря");
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

    @Override
    public void flying() {
        System.out.println("я лечу");
    }
}
