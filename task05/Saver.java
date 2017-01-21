/*
 * 2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
 * 1) в какой файл должен сохраниться текст 2) метод, который выполнит сохранение. Написать класс Saver,
 * который сохранит поле класса TextContainer в указанный файл.
 * @SaveTo(path=“c:\\file.txt”)
 * class Container {
 * String text = “…”;
 * @Saver
 * public void save(..) {…}
 * }
 */

package task05;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

@SaveTo(path = "C:\\file.txt")
class TextContainer {

    String str = "In God We Trust!";

    @SaverMethod
    public void save(String path) {
        try (FileOutputStream out = new FileOutputStream(path)) {
            out.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Saver {

    public static void main(String[] args) {
        TextContainer tc = new TextContainer();
        final Class<?> cls = tc.getClass();

        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo an = cls.getAnnotation(SaveTo.class);
            String path = an.path();

            Method[] methods = cls.getMethods();

            for (Method m : methods) {
                if (m.isAnnotationPresent(SaverMethod.class)) {
                    try {
                        m.invoke(tc, path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break; // выходим из цикла, нужный метод найден и нет смысла дальше перебирать все методы
                }
            }
        }
    }
}
