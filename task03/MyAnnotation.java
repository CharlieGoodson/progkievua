/**
 *  Пример пользовательской аннотации класса с двумя параметрами
 */

package task03;

import java.lang.annotation.*;

@Inherited
@Target(value = ElementType.TYPE) // аннотация для класса
@Retention(value = RetentionPolicy.RUNTIME) // доступна в рантайм
public @interface MyAnnotation {
    String param1() default "string"; // параметр который имеет значение по умолчанию
    String param2();
}
