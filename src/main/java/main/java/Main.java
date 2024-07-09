package main.java;

import animals.Animal;
import animals.Factory;
import data.AnimalType;
import data.Color;
import data.Command;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import tables.AbsTable;
import tables.AnimalTable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static AnimalTable animalTable = null;

    public static void main(String[] args) throws SQLException {

        animalTable = new AnimalTable(); // Initialize animalTable

        while (true) {
            System.out.println("Введите add/list/exit");
            Scanner input = new Scanner(System.in);
            String text = input.nextLine().trim().toUpperCase(Locale.ROOT);

            Command command = null;
            for (Command cmd : Command.values()) {
                if (cmd.name().equalsIgnoreCase(text)) {
                    command = cmd;
                    break;
                }
            }
            if (command == null) {
                System.out.println("неправильная команда");
                continue;
            }

            if (command == Command.ADD) {
                Animal animal;
                while (true) {
                    System.out.println("Какое животное вы хотите создать?");
                    String type = input.nextLine();
                    AnimalType animalType = AnimalType.getFromName(type);
                    if (animalType == null) {
                        System.out.println("Такого животного нет в списке");
                        for (AnimalType at : AnimalType.values()) {
                            System.out.println(at.getName());
                        }

                    } else {
                        Factory factory = new Factory();
                        animal = factory.create(animalType);
                        break;
                    }
                }

                System.out.println("Введите имя");
                String name = input.nextLine().trim();

                animal.setName(name);

                int age_int;
                while (true) {
                    System.out.println("Введите возраст");
                    String age = input.nextLine().trim();
                    try {
                        age_int = Integer.parseInt(age);
                        if (age_int > 0 && age_int < 1000) {
                            animal.setAge(age_int);
                            break;
                        } else {
                            System.out.println("Неверный возраст. Пожалуйста, введите корректный возраст.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат возраста. Пожалуйста, введите корректный возраст.");
                    }
                }
                float weight_float;
                while (true) {
                    System.out.println("Введите вес");
                    String weight = input.nextLine().trim();
                    try {
                        weight_float = Float.parseFloat(weight);
                        animal.setWeight(weight_float);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат веса. Пожалуйста, введите корректный вес.");
                    }
                }

                while (true) {
                    System.out.println("Введите цвет");
                    String personColor = input.nextLine().trim();
                    Color color = Color.getFromName(personColor);
                    if (color != null) {
                        animal.setColor(color);
                        break;
                    } else {
                        System.out.println("Неверный цвет. Пожалуйста, введите корректный цвет.");
                        for (Color c : Color.values()) {
                            System.out.println(c.getName());
                        }
                    }
                }



                animalTable.connect();
                List<String> params = new ArrayList<>();
                params.add("id INT NOT NULL PRIMARY KEY AUTO_INCREMENT");
                params.add("name VARCHAR(50) NOT NULL");
                params.add("age INT NOT NULL");
                params.add("weight FLOAT NOT NULL");
                params.add("color VARCHAR(20) DEFAULT NULL");
                params.add("type VARCHAR(50) NOT NULL");

                animalTable.create(params);
                ScriptObjectMirror animalType = null;
                animalTable.insert(name, age_int, weight_float, animal.getColor(), animalType.toString());

            } else if (command == Command.LIST) {
                System.out.println("Ваши животные: ");
                animalTable.connect();
                List<Animal> list = animalTable.selectAll();
                for (Animal myAnimal : list) {
                    System.out.println(myAnimal);
                }

            } else if (command == Command.EXIT) {
                System.exit(0);
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}