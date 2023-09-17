package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveBackTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('B');

		char[][] expectedF = readFromString("""
				FFF
				FFF
				FFF
				""");
		char[][] expectedB = readFromString("""
				BBB
				BBB
				BBB
				""");

		char[][] expectedU = readFromString("""
				RRR
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				RRD
				RRD
				RRD
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				LLL
				""");

		char[][] expectedL = readFromString("""
				ULL
				ULL
				ULL
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

		rubik.move('b');

		char[][] expectedF = readFromString("""
				FFF
				FFF
				FFF
				""");
		char[][] expectedB = readFromString("""
				BBB
				BBB
				BBB
				""");

		char[][] expectedU = readFromString("""
				LLL
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				RRU
				RRU
				RRU
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				RRR
				""");

		char[][] expectedL = readFromString("""
				DLL
				DLL
				DLL
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
