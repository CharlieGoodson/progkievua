/**
 * Простейшее использование аннотаций с рефлексией. Самотестирование методов классов.
 */

package task02;

public class Main {

	public static void main(String[] args) {

		// Создаются 2 объекта типа Class имеющихся классов SomeClass и AnotherClass,
		// и запускается статический метод из класса Tester с этими объектами в качестве параметров
		System.out.println(Tester.test(SomeClass.class, OtherClass.class));
	}	
}