/**
 * 1. Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
 * помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
 * @Test(a=2, b=5)
 * public void test(int a, int b) {…}
 */

package task04;

import java.lang.reflect.Method;

public class AnnotationDemo {

    @Test(a = 5, b = 7)
    public void test(int a, int b) {
        System.out.println("int a = " + a + ", long b = " + b);
    }

    // еще один метод добавочный на всякий случай
    public void anotherTest(int a, int b) {
        System.out.println("int a = " + a + ", long b = " + b);
    }

    public static void main(String[] args) {
        // создаем объект, т.к. все равно понадобится для вызова нестатического метода
        AnnotationDemo obj = new AnnotationDemo();
        final Class<?> cls = obj.getClass();

        Method[] methods = cls.getMethods();
        for (Method m : methods) {
            // проверяем, помечен ли метод нужной аннотацией
            if (m.isAnnotationPresent(Test.class)) {
                // получаем конкретный экземпляр аннотации
                Test an = m.getAnnotation(Test.class);
                try {
                    // вызываем метод и передаем в качестве параметров,
                    // парамтры указанные в аннотации
                    m.invoke(obj, an.a(), an.b());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
