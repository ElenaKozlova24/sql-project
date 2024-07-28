package animals;
import data.AnimalType;
import data.Color;

public abstract class Animal {

    private int id;
    private String name;
    private int age;
    private float weight;
    private Color color = null;

    public Animal(String маша, int i, float v, Color gray, AnimalType cat) {
    }

    public Animal() {

    }

    public String getName() {
        return this.name;
    }

    String getFormattedAge() {
        int ostatok = this.age % 100;
        if (ostatok >= 11 && ostatok <= 14) {
            return this.age + " лет";
        }
        ostatok = this.age % 10;
        if (ostatok == 1) {
            return this.age + " год";
        }
        if (ostatok > 1 && ostatok < 5) {
            return this.age + " года";
        }
        if (ostatok == 0 || ostatok >= 5) {
            return this.age + " лет";
        }
        return null;

    }

    public float getWeight() {
        return this.weight;

    }

    public Color getColor() {
        return this.color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void say() {
        System.out.println("я говорю");
    }

    private void drink() {
        System.out.println("я пью");
    }

    private void go() {
        System.out.println("я иду");
    }

    private void eat() {
        System.out.println("я ем");
    }

    public String toString() {
        return this.id + ": Привет! Меня зовут " + this.name + "! Мне " + getFormattedAge() +
                ", я вешу " + this.weight + ", мой цвет " + color.getName() + ".";
    }


    public void setId(int id) {
        this.id = id;
    }

    public abstract Object getAge();

    public abstract Color getType();
}
