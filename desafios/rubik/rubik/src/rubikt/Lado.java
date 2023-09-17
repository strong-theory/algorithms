package rubikt;

public enum Lado {
	U, R, D, L;

	public static boolean isHorario(Lado origem, Lado destino) {
		return origem.ordinal() >= destino.ordinal();
	}
}
