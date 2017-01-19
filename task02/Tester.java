package task02;

import java.lang.reflect.Method;

public class Tester {

    // Последовательно берется каждый объект класса и из него извлекаются все методы.
    // Затем каждый метод проверяется на аннотирование конкретной аннотацией (тут Test)
    // Если есть аннотация, метод запускается на выполнение.
    // Если все отмеченные методы возвращают true весь метод возвращает true
    // Если же хоть один метод возвратит false то весь метод возвратит false
	public static boolean test(Class<?>... ls) {
		try {
			for (Class<?> cls : ls) {
			    // тут извлекаются методы
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
				    // тут проверка аннотирован метод или нет
					if (method.isAnnotationPresent(Test.class)) {
						// если да, то он запускается
					    Boolean b = (Boolean) method.invoke(null, new Object[]{});
						// если результат false то наш метод возвращает false и завершается,
                        // и не проверяет дальше объекты даже если они есть
					    if ( ! b)
							return false;
					}
				}
			}
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
