package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveDownTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('D');

		char[][] expectedF = readFromString("""
				FFF
				FFF
				LLL
				""");
		char[][] expectedB = readFromString("""
				RRR
				BBB
				BBB
				""");

		char[][] expectedU = readFromString("""
				UUU
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				FFF
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				LLL
				LLL
				BBB
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

		rubik.move('d');

		char[][] expectedF = readFromString("""
				FFF
				FFF
				RRR
				""");
		char[][] expectedB = readFromString("""
				LLL
				BBB
				BBB
				""");

		char[][] expectedU = readFromString("""
				UUU
				UUU
				UUU
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				BBB
				""");

		char[][] expectedD = readFromString("""
				DDD
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				LLL
				LLL
				FFF
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
