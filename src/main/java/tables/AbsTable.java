package tables;

import animals.Animal;
import animals.Factory;
import data.AnimalType;
import data.Color;
import data.db.IDBCConnector;
import data.db.MYSQLDBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsTable<T> {
    private final IDBCConnector idbcConnector = new MYSQLDBConnector();
    private String name = null;


    public AbsTable(String name) throws SQLException {
        this.name = name;
    }

    public void create(List<String> columns) throws SQLException {
        if (!isTableExist()) {
            Object name = new Object();
            String sqlRequest = String.format("CREATE TABLE %s (%s)", name, String.join(", ", columns));
            idbcConnector.execute(sqlRequest);
        }
    }

    protected abstract boolean isTableExist();

    public void saveAnimals(List<Animal> animals) throws SQLException {
        String sql = "INSERT INTO animals (name, age, weight, color, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = idbcConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            if (animals != null && !animals.isEmpty()) { // проверяем, что animals не null и не пустой
                for (Animal animal : animals) {
                    System.out.println("Сохраняется животное: " + animal.getName()); // Печатаем имя животного
                    pstmt.setString(1, animal.getName());
                    pstmt.setInt(2, (Integer) animal.getAge());
                    pstmt.setDouble(3, animal.getWeight());
                    pstmt.setString(4, animal.getColor().getName());
                    pstmt.setString(5, animal.getType().getName());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            } else {
                System.out.println("Нет животных для сохранения");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении животных: " + e.getMessage());
            throw e;
        }
    }

    public void connect() throws SQLException {
        idbcConnector.connect();
    }

    public List<Animal> selectAll() throws SQLException {
        List<Animal> animals = new ArrayList<>(); // typo fixed

        String sql = "SELECT * FROM animals";
        ResultSet resultSet = idbcConnector.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            float weight = resultSet.getFloat("weight");
            String colorName = resultSet.getString("color");
            String typeName = resultSet.getString("type");

            Color color = Color.getFromName(colorName);
            AnimalType animalType = AnimalType.getFromName(typeName);
            if (animalType != null) {
                Animal animal = Factory.create(animalType);
                animal.setId(id);
                animal.setName(name);
                animal.setAge(age);
                animal.setWeight(weight);
                animal.setColor(color);

                animals.add(animal); // typo fixed
            }
        }

        return animals;
    }


    String sqlRequest = String.format("SHOW TABLES LIKE '%s'", name);
    ResultSet resultSet = idbcConnector.executeQuery(sqlRequest);

    {
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(name));}

        }


        public abstract void insert (String name,int age, float weight, Color color, String type);
    }
