package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveUpTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('U');

		char[][] expectedF = readFromString("""
				RRR
				FFF
				FFF
				""");
		char[][] expectedB = readFromString("""
				BBB
				BBB
				LLL
				""");

		char[][] expectedU = readFromString("""
				UUU
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				BBB
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				FFF
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

		rubik.move('u');

		char[][] expectedF = readFromString("""
				LLL
				FFF
				FFF
				""");
		char[][] expectedB = readFromString("""
				BBB
				BBB
				RRR
				""");

		char[][] expectedU = readFromString("""
				UUU
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				FFF
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				BBB
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
