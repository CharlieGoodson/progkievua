package task02;

public class SomeClass {

	// демонстрационный метод, который принимает параметры long  и возвращает их сумму
    public static long job(long...ls) {
		long res = 0;
		for (long l : ls)
			res += l;
		
		return res;
	}

	// метод для тестирования метода класса job()
	@Test
	public static boolean selfTest() {
		boolean res = job(1, 2, 3, 4) == 10;
		System.out.println("SomeClass: " + res);
		return res;
	}
}
