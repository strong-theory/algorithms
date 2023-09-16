import java.util.Arrays;
import java.util.List;

public class Rubik {

	public enum Lado {
		R, L, U, D;
	}

	public static void main(String[] args) {
		test("Ff", 1);
		test("FFF", 4);
		test("Rr", 1);
		test("LLL", 4);
		test("lll", 4);
		test("dl", 105);
		// test("RUUdBd", 1260);
	}

	public static int calc(String moviments) {

		var faceF = initFace('F');
		var faceB = initFace('B');
		var faceL = initFace('L');
		var faceR = initFace('R');
		var faceU = initFace('U');
		var faceD = initFace('D');

		move(faceF, faceB, faceL, faceR, faceU, faceD, moviments);

		int total = 1;

		boolean original = isPosicaoOriginal(faceF, faceB, faceL, faceR, faceU, faceD);
		while (!original) {
			move(faceF, faceB, faceL, faceR, faceU, faceD, moviments);
			original = isPosicaoOriginal(faceF, faceB, faceL, faceR, faceU, faceD);
			total++;
		}

		return total;
	}

	private static boolean isPosicaoOriginal(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR,
			char[][] faceU, char[][] faceD) {
		return validFace(faceF, 'F') && validFace(faceB, 'B') && validFace(faceL, 'L') && validFace(faceR, 'R')
				&& validFace(faceU, 'U') && validFace(faceD, 'D');
	}

	private static boolean validFace(char[][] face, char value) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (face[i][j] != value) {
					return false;
				}
			}
		}
		return true;
	}

	public static void print(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {

		System.out.println();
		for (var a : faceU) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println();
		for (var a : faceL) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println();
		for (var a : faceF) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println();
		for (var a : faceR) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println();
		for (var a : faceD) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println();
		for (var a : faceB) {
			System.out.println(Arrays.toString(a));
		}
	}

	public static void move(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD, String moviments) {

		for (int i = 0; i < moviments.length(); i++) {
			final char ch = moviments.charAt(i);
			if (ch == 'F') {
				moveF(faceF, faceB, faceL, faceR, faceU, faceD);
			} else if (ch == 'f') {
				movef(faceF, faceB, faceL, faceR, faceU, faceD);
			} else if (ch == 'L') {
				moveL(faceF, faceB, faceL, faceR, faceU, faceD);
			} else if (ch == 'l') {
				movel(faceF, faceB, faceL, faceR, faceU, faceD);
			} else if (ch == 'd') {
				moved(faceF, faceB, faceL, faceR, faceU, faceD);
			}
		}
	}

	public static void moveHorario(List<char[][]> origins, List<char[][]> destinos) {

		var copies = destinos.stream().map(Rubik::copy).toList();

		move(origins.get(0), Lado.D, copies.get(0), Lado.R);
		move(origins.get(1), Lado.L, copies.get(0), Lado.D);
		move(origins.get(2), Lado.U, copies.get(0), Lado.L);
		move(origins.get(3), Lado.R, copies.get(0), Lado.U);
	}
//	
//	public static void moveF(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
//			char[][] faceD) {
//		moveHorario(List.of(faceU, faceR, faceD, faceL), List.of(faceL, faceU, faceR, faceD));
//	}

	public static void moveF(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {

		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);

		move(faceU, Lado.D, copyL, Lado.R);
		move(faceR, Lado.L, copyU, Lado.D);
		move(faceD, Lado.U, copyR, Lado.L);
		move(faceL, Lado.R, copyD, Lado.U);
	}

	public static void moveL(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceU, Lado.L, copyB, Lado.L);
		move(faceF, Lado.L, copyU, Lado.L);
		move(faceD, Lado.L, copyF, Lado.L);
		move(faceB, Lado.L, copyD, Lado.L);
	}

	public static void moveB(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {
		
		var copyR = copy(faceR);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);
		
		move(faceU, Lado.U, copyR, Lado.U);
		move(faceL, Lado.U, copyU, Lado.U);
		move(faceD, Lado.U, copyF, Lado.L);
		move(faceB, Lado.R, copyD, Lado.U);
	}

	public static void movef(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {

		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);

		move(faceU, Lado.D, copyR, Lado.L);
		move(faceR, Lado.L, copyD, Lado.U);
		move(faceD, Lado.U, copyL, Lado.R);
		move(faceL, Lado.R, copyU, Lado.D);
	}

	public static void movel(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceU, Lado.D, copyF, Lado.L);
		move(faceF, Lado.L, copyD, Lado.U);
		move(faceD, Lado.U, copyB, Lado.R);
		move(faceB, Lado.R, copyU, Lado.D);
	}

	public static void moved(char[][] faceF, char[][] faceB, char[][] faceL, char[][] faceR, char[][] faceU,
			char[][] faceD) {
		
		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);
		
		move(faceU, Lado.L, copyF, Lado.L);
		move(faceF, Lado.D, copyD, Lado.U);
		move(faceD, Lado.R, copyB, Lado.R);
		move(faceB, Lado.U, copyU, Lado.D);
	}

	private static char[][] copy(char[][] faceF) {

		var copy = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copy[i][j] = faceF[i][j];
			}
		}
		return copy;
	}

	public static void move(char[][] destino, Lado ladoDestino, char[][] origem, Lado ladoOrigem) {
		final char[] valuesOrigem = valuesOrigem(origem, ladoOrigem);

		setValuesDestino(valuesOrigem, destino, ladoDestino);
	}

	public static void setValuesDestino(char[] valuesOrigem, char[][] destino, Lado ladoDestino) {
		if (Lado.U.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[0][i] = valuesOrigem[i];
			}
		}

		if (Lado.D.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[2][i] = valuesOrigem[i];
			}
		}

		if (Lado.L.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[i][0] = valuesOrigem[i];
			}
		}

		if (Lado.R.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[i][2] = valuesOrigem[i];
			}
		}
	}

	public static char[] valuesOrigem(char[][] origem, Lado ladoOrigem) {
		char[] result = new char[3];
		if (Lado.U.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[0][i];
			}
		}

		if (Lado.D.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[2][i];
			}
		}

		if (Lado.L.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[i][0];
			}
		}

		if (Lado.R.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[i][2];
			}
		}

		return result;
	}

	public static char[][] initFace(char ch) {
		var face = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				face[i][j] = ch;
			}
		}
		return face;
	}

	public static void test(String moviments, int expected) {
		int result = calc(moviments);

		if (result == expected) {
			System.out.println("OK");
		} else {
			System.err.println("Error. Expected: " + expected + " result: " + result);
		}
	}
}