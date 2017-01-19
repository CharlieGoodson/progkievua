/**
 *  Пример пользовательской аннотации метода
 */

package task03;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD) // аннотация для методов
@Retention(value = RetentionPolicy.RUNTIME) // доступна в рантайм
public @interface MethodAnnotation {}
