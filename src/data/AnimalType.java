package data;

public enum AnimalType {
    DOG("собака"),
    CAT("кошка"),
    DUCK("утка");

    private final String name;

    AnimalType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AnimalType getFromName(String name) {
        for (AnimalType animal : AnimalType.values()) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }
}
