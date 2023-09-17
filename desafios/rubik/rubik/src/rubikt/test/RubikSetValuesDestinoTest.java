package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Lado;
import rubikt.Rubik;

public class RubikSetValuesDestinoTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testLadoD() {

		char[] origem = { 'A', 'B', 'C' };

		char[][] destino = readFromString("""
				ABC
				DEF
				GHI
				""");

		rubik.setValuesDestinoHorario(origem, destino, Lado.D);

		char[][] expected = readFromString("""
				ABC
				DEF
				CBA
				""");

		rubik.printFace(destino);

		assertArrayEquals(expected, destino);
	}

	@Test
	public void testLadoU() {

		char[] origem = { '1', '2', '3' };

		char[][] destino = readFromString("""
				ABC
				DEF
				GHI
				""");

		rubik.setValuesDestinoHorario(origem, destino, Lado.U);

		char[][] expected = readFromString("""
				321
				DEF
				GHI
				""");

		assertArrayEquals(expected, destino);
	}

	@Test
	public void testValuesOrigemLeft() {

		char[][] origem = readFromString("""
				AFF
				BFF
				CFF
				""");
		var array = rubik.valuesOrigem(origem, Lado.L);

		char[] expected = { 'A', 'B', 'C' };

		assertArrayEquals(expected, array);
	}

	@Test
	public void testValuesOrigemRight() {

		char[][] origem = readFromString("""
				ABC
				DEF
				GHI
				""");
		var array = rubik.valuesOrigem(origem, Lado.R);

		char[] expected = { 'C', 'F', 'I' };

		assertArrayEquals(expected, array);
	}

	@Test
	public void testValuesOrigemUp() {

		char[][] origem = readFromString("""
				ABC
				DEF
				GHI
				""");
		var array = rubik.valuesOrigem(origem, Lado.U);

		char[] expected = { 'A', 'B', 'C' };

		assertArrayEquals(expected, array);
	}

	@Test
	public void testValuesOrigemDown() {

		char[][] origem = readFromString("""
				ABC
				DEF
				GHI
				""");
		var array = rubik.valuesOrigem(origem, Lado.D);

		char[] expected = { 'G', 'H', 'I' };

		assertArrayEquals(expected, array);
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
