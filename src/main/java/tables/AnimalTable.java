package tables;

import animals.Animal;

import java.sql.SQLException;

public class AnimalTable extends AbsTable<Animal> {

    public AnimalTable() throws SQLException {
        super("animals");
    }

    @Override
    protected boolean isTableExist() {
        return super.isTableExist();
    }

    @Override
    public void insert(String name, int age, float weight, String color, String type) {

            create(name, age, weight, color, type);

    }
}
