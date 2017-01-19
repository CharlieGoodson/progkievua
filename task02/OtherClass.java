package task02;

public class OtherClass {

	// демонстрационный метод, который принимает параметры String и возвращает их сумму
	public static String work(String... ls) {
		StringBuilder sb = new StringBuilder();
		for (String s : ls)
			sb.append(s);
		
		return sb.toString();
	}

	// метод для тестирования метода класса work()
	@Test
	public static boolean testMethod() {
		boolean res = work("1", "22", "333").equals("122333");
		System.out.println("OtherClass: " + res);
		return res;
	}
}