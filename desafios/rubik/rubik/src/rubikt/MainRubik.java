package rubikt;

public class MainRubik {

	public static void main(String[] args) {
		test("RUUdBd", 1260);

	}

	public static void test(String moviments, int expected) {
		Rubik rubik = new Rubik();
		int result = rubik.calc(moviments);

		if (result == expected) {
			System.out.println("OK");
		} else {
			System.err.println("Error. Expected: " + expected + " result: " + result);
		}
	}

}
