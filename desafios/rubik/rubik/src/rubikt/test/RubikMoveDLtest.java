package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveDLtest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testdl() {

		rubik.move('D');
		rubik.move('L');

		char[][] expectedR = readFromString("""
				RRR
				RRR
				FFF
				""");

		char[][] expectedL = readFromString("""
				BLL
				BLL
				BLL
				""");

		char[][] expectedF = readFromString("""
				UFF
				UFF
				ULL
				""");
		char[][] expectedB = readFromString("""
				DRR
				DBB
				DBB
				""");

		char[][] expectedU = readFromString("""
				RUU
				BUU
				BUU
				""");

		char[][] expectedD = readFromString("""
				FDD
				FDD
				LDD
				""");

		assertArrayEquals(expectedL, rubik.getFaceL());
		assertArrayEquals(expectedR, rubik.getFaceR());

		assertArrayEquals(expectedU, rubik.getFaceU());
		assertArrayEquals(expectedF, rubik.getFaceF());
		assertArrayEquals(expectedB, rubik.getFaceB());
		assertArrayEquals(expectedD, rubik.getFaceD());
	}

	private char[][] readFromString(String s) {
		char[][] result = new char[3][3];

		var lines = s.split("\n");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i][j] = lines[i].charAt(j);
			}
		}

		return result;
	}

}
