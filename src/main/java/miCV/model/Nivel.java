package miCV.model;

public enum Nivel {

	BASICO(0), MEDIO(1), AVANZADO(2);

	private int id;

	private Nivel(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static Nivel findById(int id) {
		for (Nivel niv : values())
			if (id == niv.getId())
				return niv;
		throw new IllegalArgumentException("No hay un Nivel con id " + id);
	}
}
