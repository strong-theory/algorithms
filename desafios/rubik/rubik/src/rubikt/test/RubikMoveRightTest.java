package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveRightTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		rubik.move('R');

		char[][] expectedF = readFromString("""
				FFD
				FFD
				FFD
				""");
		char[][] expectedB = readFromString("""
				BBU
				BBU
				BBU
				""");

		char[][] expectedU = readFromString("""
				UUF
				UUF
				UUF
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				DDB
				DDB
				DDB
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

		rubik.move('r');

		char[][] expectedF = readFromString("""
				FFU
				FFU
				FFU
				""");
		char[][] expectedB = readFromString("""
				BBD
				BBD
				BBD
				""");

		char[][] expectedU = readFromString("""
				UUB
				UUB
				UUB
				""");

		char[][] expectedR = readFromString("""
				RRR
				RRR
				RRR
				""");

		char[][] expectedD = readFromString("""
				DDF
				DDF
				DDF
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
