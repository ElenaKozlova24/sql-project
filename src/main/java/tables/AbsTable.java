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
            String sqlRequest = String.format("CREATE TABLE Animals (" +
                    "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "weight FLOAT NOT NULL, " +
                    "color VARCHAR(20) DEFAULT NULL, " +
                    "type VARCHAR(50) NOT NULL" +
                    ")");
            idbcConnector.execute(sqlRequest);
        }
    }

    protected boolean isTableExist() {
        String sqlRequest = String.format("SHOW TABLES LIKE '%s'", name);
        try (ResultSet resultSet = idbcConnector.executeQuery(sqlRequest)) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void create(String name, int age, float weight, String color, String type) {
        String sql = "INSERT INTO " + this.name + " (name, age, weight, color, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = idbcConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);
                    pstmt.setDouble(3, weight);
                    pstmt.setString(4, color);
                    pstmt.setString(5, type);
                    pstmt.addBatch();
                    pstmt.executeBatch();

        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public void updateAnimal(int id, String name, int age, float weight, String color) throws SQLException {
        String sql = "UPDATE animals SET name = ?, age = ?, weight = ?, color = ? WHERE id = ?";
        try (Connection connection = idbcConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, weight);
            pstmt.setString(4, color);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении животного: " + e.getMessage());
            throw e;
        }
    }



    public void connect() {
        idbcConnector.connect();
    }

    public List<Animal> selectAll() throws SQLException {
        List<Animal> animals = new ArrayList<>();

        String sql = "SELECT * FROM animals";
        ResultSet resultSet = idbcConnector.executeQuery(sql);

        toAnimal(animals, resultSet);

        return animals;
    }

    public List<Animal> selectAllByFilter(String filter) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE type = '" + filter + "'";
        ResultSet resultSet = idbcConnector.executeQuery(sql);
        toAnimal(animals, resultSet);

        return animals;
    }

    public Animal selectById(int id) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE id = '" + id + "'";
        ResultSet resultSet = idbcConnector.executeQuery(sql);
        toAnimal(animals, resultSet);
        return animals.get(0);
    }

    private void toAnimal(List<Animal> animals, ResultSet resultSet) throws SQLException {
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
                animals.add(animal);
            }
        }
    }


    String sqlRequest = String.format("SHOW TABLES LIKE '%s'", name);
    ResultSet resultSet = idbcConnector.executeQuery(sqlRequest);

    {
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(name));}

        }


        public abstract void insert (String name,int age, float weight, String color, String type);
    }
