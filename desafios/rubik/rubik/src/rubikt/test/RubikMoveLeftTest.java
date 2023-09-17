package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveLeftTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('L');

		char[][] expectedF = readFromString("""
				UFF
				UFF
				UFF
				""");
		char[][] expectedB = readFromString("""
				DBB
				DBB
				DBB
				""");

		char[][] expectedU = readFromString("""
				BUU
				BUU
				BUU
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				FDD
				FDD
				FDD
				""");

		char[][] expectedL = readFromString("""
				LLL
				LLL
				LLL
				""");

		assertArrayEquals(expectedF, rubik.getFaceF());
		assertArrayEquals(expectedB, rubik.getFaceB());
		assertArrayEquals(expectedU, rubik.getFaceU());
		assertArrayEquals(expectedR, rubik.getFaceR());
		assertArrayEquals(expectedD, rubik.getFaceD());
		assertArrayEquals(expectedL, rubik.getFaceL());
	}

	@Test
	public void testAntiHorario() {

		rubik.move('l');

		char[][] expectedF = readFromString("""
				DFF
				DFF
				DFF
				""");
		char[][] expectedB = readFromString("""
				UBB
				UBB
				UBB
				""");

		char[][] expectedU = readFromString("""
				FUU
				FUU
				FUU
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				BDD
				BDD
				BDD
				""");

		char[][] expectedL = readFromString("""
				LLL
				LLL
				LLL
				""");

		assertArrayEquals(expectedF, rubik.getFaceF());
		assertArrayEquals(expectedB, rubik.getFaceB());
		assertArrayEquals(expectedU, rubik.getFaceU());
		assertArrayEquals(expectedR, rubik.getFaceR());
		assertArrayEquals(expectedD, rubik.getFaceD());
		assertArrayEquals(expectedL, rubik.getFaceL());
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
