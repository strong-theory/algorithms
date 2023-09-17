package rubikt.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikMoveRUUdBdtest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testdl() {

		rubik.move('R');
		rubik.print();
		
		System.out.println("*******************************");
		rubik.moveU();
		rubik.print();

		char[][] expectedR = readFromString("""
				UBB
				RRR
				RRR
				""");

		char[][] expectedL = readFromString("""
				FFD
				LLL
				LLL
				""");

		char[][] expectedF = readFromString("""
				RRR
				FFD
				FFD
				""");
		char[][] expectedB = readFromString("""
				BBU
				BBU
				RRU
				""");

		char[][] expectedU = readFromString("""
				UUU
				UUU
				FFF
				""");

		char[][] expectedD = readFromString("""
				DDB
				DDB
				DDB
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
