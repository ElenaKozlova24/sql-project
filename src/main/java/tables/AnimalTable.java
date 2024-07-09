package tables;

import animals.Animal;
import data.AnimalType;
import data.Color;
import animals.Factory;
import data.db.IDBCConnector;
import tables.AnimalTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable<Animal> {

    public AnimalTable() throws SQLException {
        super("animals");
    }


    @Override
    protected boolean isTableExist() {
        return false;
    }

    @Override
    public void insert(String name, int age_int, float weight, Color color, String s) {

    }
}
