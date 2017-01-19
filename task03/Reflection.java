package task03;

import java.lang.reflect.Method;

@MyAnnotation(param2 = "test")
class Test {
    @MethodAnnotation
    public void myMethod() {}
}

public class Reflection {
    public static void main(String[] args) {

        final Class<?> cls = Test.class;

        // проверка является ли класс помеченным аннотацией MyAnnotation
        if (cls.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("Class is annotated");
        }

        // получение объекта класса аннотации MyAnnotation
        MyAnnotation an = cls.getAnnotation(MyAnnotation.class);
        System.out.println("param1 = " + an.param1() + ", param2 = " + an.param2());

        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            // проверка помечен ли метод конкретной аннотацией
            if (method.isAnnotationPresent(MethodAnnotation.class)) {
                // если да, то вывести имя этого метода
                System.out.println("Method " + method.getName() + "() is marked with MethodAnnotation");
            }
        }
    }
}
