/**
 * Листинг демонстрирующий элементарные операции из пакета Reflect (Рефлексия)
 *
 * Created on 19.01.2017.
 */

package task01;

import java.lang.reflect.*;

public class Reflection {

    // Создаем простой статический вложеный класс
    static private final class Test {
        public String p = "Test string";
        private int a = 7;
        protected long b = 8;

        public Test() {}
        public Test(int a) { this.a = a; }
        public Test(int a, long b) { this.a = a; this.b = b; }
        public int getA() { return a; }
        public long getB() { return b; }
        public void setA(int a) { this.a = a; }
    }

    public static void main(String[] args) {

        // объявляем переменную типа Class и кладем в нее экземпляр объекта Class нашего класса Test (см. выше)
        final Class<?> cls = Test.class;
        // выводим на консоль имя нашего класса Test
        System.out.println("Class name: " + cls.getName());

        System.out.print("Modifiers: ");
        // теперь получаем модификаторы доступа для нашего класса в виде индекса int
        int mods = cls.getModifiers();
        // далее выборочно проверяем имеет ли наш класс некоторые из указанных модификаторов
        if (Modifier.isPrivate(mods))
            System.out.print("private ");
        if (Modifier.isAbstract(mods))
            System.out.print("abstract ");
        if (Modifier.isFinal(mods))
            System.out.print("final\n");

        System.out.println("All fields:");
        // получаем список полей нашего класса в виде массива из объектов типа Field
        Field[] fields = cls.getDeclaredFields();
        // из этого массива с помощью цикла выводим список имен полей и их типов
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            System.out.print("\tName: " + field.getName());
            System.out.println("\tType: " + fieldType.getName());
        }

        System.out.println("Constructors:");
        // аналогично, получаем список конструкторов нашего класса в виде массива объектов типа Constructor<?>
        Constructor<?>[] constrs = cls.getConstructors();
        for (Constructor<?> ctr : constrs) {
            // из каждого конструктора получаем список типов параметров в виде массива
            Class<?>[] paramTypes = ctr.getParameterTypes();
            // с помощью вложенного цикла выводим их список
            for (Class<?> paramType : paramTypes) {
                System.out.print(paramType.getName() + " ");
            }
            System.out.println();
        }

        try {
            // создаем массив объектов типа Class и кладем туда один элемент int.class
            Class<?>[] paramTypes = new Class<?>[] { int.class };
            // получаем конкретный конструктор по списку праметров который мы создали (см. выше)
            Constructor<?> ctr = cls.getConstructor(paramTypes);
            // создаем обычный экземпляр класса с помощью этого конструктора и одним параметром int
            Test t = (Test)ctr.newInstance(1);
            // теперь для проверки с помощью геттеров выводим на консоль поля класса
            System.out.println("Fields: " + t.getA() + ", " + t.getB());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // получаем список методов класса в виде массива объектов типа Method
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            // достаем имя метода
            System.out.println("Name: " + method.getName());
            // достаем возвращаемый тип
            System.out.println("\tReturn type: " + method.getReturnType().getName());
            // получаем список аргументов каждого метода в видем массива
            Class<?>[] paramTypes = method.getParameterTypes();
            System.out.print("\tParam types:");
            // и выводим их в цикле
            for (Class<?> paramType : paramTypes) {
                System.out.print(" " + paramType.getName());
            }
            System.out.println();
        }

        try {
            // создаем экземляр класса
            Test obj = new Test();
            // создаем список параметров из одного int'а
            Class<?>[] paramTypes = new Class<?>[] { int.class };
            // по имени и списку параметров получаем объект конкретного метода из класса
            Method method = cls.getMethod("setA", paramTypes);
            // вызываем этот метод из указанного объекта с указанным параметром
            method.invoke(obj, 55);
            // проверяме правильно ли отработал метод
            System.out.println("A: " + obj.getA());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // здесь все тоже самое но выскакивает исключение "метод не найден", т.к. указанный в вызове
        // метод действительно не существует
        try {
            Test obj = new Test();
            Class<?>[] paramTypes = new Class<?>[] { int.class };
            Method method = cls.getMethod("someUnknownMethod", paramTypes);
            method.invoke(obj, 55);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Test obj = new Test();
            // получаем поле по имени
            Field field = cls.getDeclaredField("a");
            // т.к. поле объявлено как private делаем его видимым
            field.setAccessible(true);
            // извлекаем значение поля из конкретного объекта созданного ранее
            System.out.println("Private field value: " + field.getInt(obj));
            // устанавливаем новое значение
            field.setInt(obj, 100);
            // и для проверки извлекаем его заново
            System.out.println("New private field value: " + field.getInt(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}