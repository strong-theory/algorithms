package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoverFaceTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testHorario() {

		char[][] origem = readFromString("""
				123
				456
				789
				""");

		rubik.moveHorario(origem);

		char[][] expected = readFromString("""
				741
				852
				963
				""");

		assertArrayEquals(expected, origem);
	}

	@Test
	public void testAntiHorario() {

		char[][] origem = readFromString("""
				123
				456
				789
				""");

		rubik.moveAntiHorario(origem);

		char[][] expected = readFromString("""
				369
				258
				147
				""");

		assertArrayEquals(expected, origem);
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
