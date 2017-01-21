package task06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;

public class DataOfPerson {

    @Save
    public String name;
    @Save
    private String surname;
    @Save
    private int age;

    protected double weight;
    @Save
    public double height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Data of Person: Name - " + getName() + ", Surname - " + getSurname() + ", Age - " + getAge()
                + ", Weight - " + getWeight() + ", Height - " + getHeight();
    }

    public void serialize(String filePath) {
        final Class<?> cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        String fieldsString = "";
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    fieldsString += field.getName() + ":";
                    if (field.getType() == int.class) {
                        fieldsString += field.getInt(this) + ";";
                    } else if (field.getType() == double.class) {
                        fieldsString += field.getDouble(this) + ";";
                    } else {
                        fieldsString += field.get(this) + ";";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(fieldsString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DataOfPerson deSerialize(String filePath) {
        String dataString = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            dataString = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String[] fields = dataString.split(";");
        DataOfPerson res = new DataOfPerson();
        final Class<?> cls = res.getClass();
        for (String field : fields) {
            String[] data = field.split(":");
            try {
                Field f = cls.getDeclaredField(data[0]);
                f.setAccessible(true);
                Class<?> type = f.getType();
                if (type == int.class) {
                    f.setInt(res, Integer.parseInt(data[1]));
                } else if (type == double.class) {
                    f.setDouble(res, Double.parseDouble(data[1]));
                } else {
                    f.set(res, data[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return res;
    }
}
