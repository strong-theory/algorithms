package rubikt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rubikt.Rubik;

public class RubikTest {

	Rubik rubik;

	@BeforeEach
	void setup() {
		rubik = new Rubik();
	}

	@Test
	public void testdl() {
		assertEquals(105, rubik.calc("dl"));
	}

	@Test
	public void testDL() {
		assertEquals(105, rubik.calc("DL"));
	}

	@Test
	public void testRUUdBd() {
		assertEquals(1260, rubik.calc("RUUdBd"));
	}

	@Test
	public void testU() {
		assertEquals(4, rubik.calc("UUU"));
		assertEquals(4, rubik.calc("uuu"));
		assertEquals(1, rubik.calc("uU"));
		assertEquals(1, rubik.calc("Uu"));
	}

	@Test
	public void testD() {
		assertEquals(4, rubik.calc("DDD"));
		assertEquals(4, rubik.calc("ddd"));
		assertEquals(1, rubik.calc("dD"));
		assertEquals(1, rubik.calc("Dd"));
	}

	@Test
	public void testF() {
		assertEquals(4, rubik.calc("FFF"));
		assertEquals(4, rubik.calc("fff"));
		assertEquals(1, rubik.calc("fF"));
		assertEquals(1, rubik.calc("Ff"));
	}

	@Test
	public void testR() {
		assertEquals(4, rubik.calc("RRR"));
		assertEquals(4, rubik.calc("rrr"));
		assertEquals(1, rubik.calc("rR"));
		assertEquals(1, rubik.calc("Rr"));
	}

	@Test
	public void testL() {
		assertEquals(4, rubik.calc("LLL"));
		assertEquals(4, rubik.calc("lll"));
		assertEquals(1, rubik.calc("lL"));
		assertEquals(1, rubik.calc("Ll"));
	}
}
