package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveFrontTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('F');

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
				UUU
				UUU
				LLL
				""");

		char[][] expectedR = readFromString("""
				URR
				URR
				URR
				""");

		char[][] expectedD = readFromString("""
				RRR
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				LLD
				LLD
				LLD
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

		rubik.move('f');

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
				UUU
				UUU
				RRR
				""");

		char[][] expectedR = readFromString("""
				DRR
				DRR
				DRR
				""");

		char[][] expectedD = readFromString("""
				LLL
				DDD
				DDD
				""");

		char[][] expectedL = readFromString("""
				LLU
				LLU
				LLU
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
