package rubikt;

import java.util.Arrays;

public class Rubik {

	private final char[][] faceF;
	private final char[][] faceB;
	private final char[][] faceL;
	private final char[][] faceR;
	private final char[][] faceU;
	private final char[][] faceD;

	public Rubik() {
		faceF = initFace('F');
		faceB = initFace('B');
		faceL = initFace('L');
		faceR = initFace('R');
		faceU = initFace('U');
		faceD = initFace('D');
	}

	public char[][] getFaceF() {
		return faceF;
	}

	public char[][] getFaceB() {
		return faceB;
	}

	public char[][] getFaceL() {
		return faceL;
	}

	public char[][] getFaceR() {
		return faceR;
	}

	public char[][] getFaceU() {
		return faceU;
	}

	public char[][] getFaceD() {
		return faceD;
	}

	public int calc(final String moviments) {

		move(moviments);

		int total = 1;

		boolean original = isPosicaoOriginal();
		while (!original) {
			move(moviments);
			original = isPosicaoOriginal();
			total++;
			System.out.println(total);
		}

		return total;
	}

	private boolean isPosicaoOriginal() {
		return validFace(faceF, 'F') && validFace(faceB, 'B') && validFace(faceL, 'L') && validFace(faceR, 'R')
				&& validFace(faceU, 'U') && validFace(faceD, 'D');
	}

	private static boolean validFace(char[][] face, char value) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (face[i][j] != value) {
					return false;
				}
			}
		}
		return true;
	}

	public void print() {

		System.out.println("U");
		for (var a : faceU) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("L");
		for (var a : faceL) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("F");
		for (var a : faceF) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("R");
		for (var a : faceR) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("D");
		for (var a : faceD) {
			System.out.println(Arrays.toString(a));
		}

		System.out.println("B");
		for (var a : faceB) {
			System.out.println(Arrays.toString(a));
		}
	}

	public void printFace(char[][] face) {
		for (var l : face) {
			System.out.println(Arrays.toString(l));
		}
	}

	public void move(final String moviments) {

		for (int i = 0; i < moviments.length(); i++) {
			final char ch = moviments.charAt(i);
			move(ch);
		}
	}

	public void move(final char ch) {
		if (ch == 'F') {
			moveF();
		} else if (ch == 'f') {
			movef();
		} else if (ch == 'L') {
			moveL();
		} else if (ch == 'l') {
			movel();
		} else if (ch == 'R') {
			moveR();
		} else if (ch == 'r') {
			mover();
		} else if (ch == 'D') {
			moveD();
		} else if (ch == 'd') {
			moved();
		} else if (ch == 'U') {
			moveU();
		} else if (ch == 'u') {
			moveu();
		} else if (ch == 'B') {
			moveB();
		} else if (ch == 'b') {
			moveb();
		}
	}

	public void moveHorario(final char[][] face) {
		final var copyFace = copy(face);
		face[0][0] = copyFace[2][0];
		face[0][1] = copyFace[1][0];
		face[0][2] = copyFace[0][0];
		face[1][0] = copyFace[2][1];
		face[1][1] = copyFace[1][1];
		face[1][2] = copyFace[0][1];
		face[2][0] = copyFace[2][2];
		face[2][1] = copyFace[1][2];
		face[2][2] = copyFace[0][2];
	}

	public void moveAntiHorario(final char[][] face) {
		final var copyFace = copy(face);
		face[0][0] = copyFace[0][2];
		face[0][1] = copyFace[1][2];
		face[0][2] = copyFace[2][2];
		face[1][0] = copyFace[0][1];
		face[1][1] = copyFace[1][1];
		face[1][2] = copyFace[2][1];
		face[2][0] = copyFace[0][0];
		face[2][1] = copyFace[1][0];
		face[2][2] = copyFace[2][0];
	}

	public void moveF() {

		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);

		move(faceU, Lado.D, copyL, Lado.R);
		move(faceR, Lado.L, copyU, Lado.D);
		move(faceD, Lado.U, copyR, Lado.L);
		move(faceL, Lado.R, copyD, Lado.U);

		moveHorario(faceF);
	}


	public void movef() {

		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);

		move(faceU, Lado.D, copyR, Lado.L);
		move(faceR, Lado.L, copyD, Lado.U);
		move(faceD, Lado.U, copyL, Lado.R);
		move(faceL, Lado.R, copyU, Lado.D);

		moveAntiHorario(faceF);
	}
	

	public void moveB() {

		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);

		move(faceL, Lado.L, copyU, Lado.U);
		move(faceD, Lado.D, copyL, Lado.L);
		move(faceR, Lado.R, copyD, Lado.U);
		move(faceU, Lado.U, copyR, Lado.R);

		moveHorario(faceB);
	}

	public void moveb() {
		
		var copyL = copy(faceL);
		var copyU = copy(faceU);
		var copyR = copy(faceR);
		var copyD = copy(faceD);
		
		move(faceL, Lado.L, copyD, Lado.U);
		move(faceD, Lado.D, copyR, Lado.L);
		move(faceR, Lado.R, copyU, Lado.U);
		move(faceU, Lado.U, copyL, Lado.R);
		
		moveAntiHorario(faceB);
	}

	public void moveL() {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceF, Lado.L, copyU, Lado.L);
		move(faceD, Lado.L, copyF, Lado.L);
		move(faceB, Lado.L, copyD, Lado.L);
		move(faceU, Lado.L, copyB, Lado.L);

		moveHorario(faceL);
	}

	public void movel() {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceF, Lado.L, copyD, Lado.L);
		move(faceD, Lado.L, copyB, Lado.L);
		move(faceB, Lado.L, copyU, Lado.L);
		move(faceU, Lado.L, copyF, Lado.L);

		moveAntiHorario(faceL);
	}

	public void moveR() {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceF, Lado.R, copyD, Lado.R);
		move(faceD, Lado.R, copyB, Lado.R);
		move(faceB, Lado.R, copyU, Lado.R);
		move(faceU, Lado.R, copyF, Lado.R);

		moveHorario(faceR);
	}

	public void mover() {

		var copyB = copy(faceB);
		var copyU = copy(faceU);
		var copyF = copy(faceF);
		var copyD = copy(faceD);

		move(faceF, Lado.R, copyU, Lado.R);
		move(faceD, Lado.R, copyF, Lado.R);
		move(faceB, Lado.R, copyD, Lado.R);
		move(faceU, Lado.R, copyB, Lado.R);

		moveAntiHorario(faceR);
	}

	public void moveD() {

		var copyL = copy(faceL);
		var copyF = copy(faceF);
		var copyR = copy(faceR);
		var copyB = copy(faceB);

		move(faceF, Lado.D, copyL, Lado.D);
		move(faceR, Lado.D, copyF, Lado.D);
		move(faceB, Lado.U, copyR, Lado.D);
		move(faceL, Lado.D, copyB, Lado.U);

		moveHorario(faceD);
	}

	public void moved() {

		var copyL = copy(faceL);
		var copyF = copy(faceF);
		var copyR = copy(faceR);
		var copyB = copy(faceB);

		move(faceF, Lado.D, copyR, Lado.D);
		move(faceR, Lado.D, copyB, Lado.U);
		move(faceB, Lado.U, copyL, Lado.D);
		move(faceL, Lado.D, copyF, Lado.D);

		moveAntiHorario(faceD);
	}

	public void moveU() {

		var copyL = copy(faceL);
		var copyF = copy(faceF);
		var copyR = copy(faceR);
		var copyB = copy(faceB);

		move(faceF, Lado.U, copyR, Lado.U);
		move(faceL, Lado.U, copyF, Lado.U);
		move(faceB, Lado.D, copyL, Lado.U);
		move(faceR, Lado.U, copyB, Lado.D);

		moveHorario(faceU);
	}

	public void moveu() {

		var copyL = copy(faceL);
		var copyF = copy(faceF);
		var copyR = copy(faceR);
		var copyB = copy(faceB);

		move(faceF, Lado.U, copyL, Lado.U);
		move(faceR, Lado.U, copyF, Lado.U);
		move(faceB, Lado.D, copyR, Lado.U);
		move(faceL, Lado.U, copyB, Lado.D);

		moveAntiHorario(faceU);
	}

	private static char[][] copy(char[][] face) {

		var copy = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copy[i][j] = face[i][j];
			}
		}
		return copy;
	}

	private void move(char[][] destino, Lado ladoDestino, char[][] origem, Lado ladoOrigem) {
		final char[] valuesOrigem = valuesOrigem(origem, ladoOrigem);

		boolean isHorario = Lado.isHorario(ladoOrigem, ladoDestino);

		if (isHorario) {
			setValuesDestinoHorario(valuesOrigem, destino, ladoDestino);
		} else {
			setValuesDestinoAntiHorario(valuesOrigem, destino, ladoDestino);
		}

	}

	public void setValuesDestinoHorario(char[] valuesOrigem, char[][] destino, Lado ladoDestino) {
		if (Lado.U.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[0][i] = valuesOrigem[i];
			}
		}

		if (Lado.D.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[2][2 - i] = valuesOrigem[i];
			}
		}

		if (Lado.L.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[i][0] = valuesOrigem[i];
			}
		}

		if (Lado.R.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[i][2] = valuesOrigem[i];
			}
		}
	}

	public void setValuesDestinoAntiHorario(char[] valuesOrigem, char[][] destino, Lado ladoDestino) {
		if (Lado.U.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[0][i] = valuesOrigem[i];
			}
		}

		if (Lado.D.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[2][i] = valuesOrigem[i];
			}
		}

		if (Lado.L.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[2 - i][0] = valuesOrigem[i];
			}
		}

		if (Lado.R.equals(ladoDestino)) {
			for (int i = 0; i < 3; i++) {
				destino[2 - i][2] = valuesOrigem[i];
			}
		}
	}

	public char[] valuesOrigem(char[][] origem, Lado ladoOrigem) {
		char[] result = new char[3];
		if (Lado.U.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[0][i];
			}
		}

		if (Lado.D.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[2][i];
			}
		}

		if (Lado.L.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[i][0];
			}
		}

		if (Lado.R.equals(ladoOrigem)) {
			for (int i = 0; i < 3; i++) {
				result[i] = origem[i][2];
			}
		}

		return result;
	}

	private char[][] initFace(char ch) {
		var face = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				face[i][j] = ch;
			}
		}
		return face;
	}

}