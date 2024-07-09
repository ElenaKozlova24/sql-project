package data;

public enum Color {
    BLACK("чёрный"),
    WHITE("белый"),
    GRAY("серый"),
    BROWN("коричневый"),
    RED("красный"),
    GREEN("зелёный"),
    BLUE("синий");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Color getFromName(String name) {
        for (Color color : Color.values()) {
            if (color.getName().equalsIgnoreCase(name)) {
                return color;
            }
        }
        return null;
    }
}