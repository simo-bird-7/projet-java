package parking.business;

public class Camion extends Vehicule{

	public Camion(String imm, String mod, String mar, String prop) {
		super(imm, mod, mar, prop);
	}

	public boolean isTransporteur() {
		return true;
	}

}
