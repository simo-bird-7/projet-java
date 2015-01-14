package parking.business;

public class Voiture extends Vehicule{

	public Voiture(String imm, String mod, String mar, String prop) {
		super(imm, mod, mar, prop);
	}

	public boolean isTransporteur() {
		return false;
	}

}
