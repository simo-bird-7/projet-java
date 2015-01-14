package parking.business;

public abstract class Vehicule {
	
	String immatriculation;
	String modele;
	String marque;
	String proprietaire;

	public Vehicule(String imm, String mod, String mar, String prop){
		this.immatriculation = imm;
		this.modele = mod;
		this.marque = mar;
		this.proprietaire = prop;
	}
	
	public abstract boolean isTransporteur();
	
	public String getImmatriculation()
	{
		return immatriculation;
	}
	
	public String getModele()
	{
		return modele;
	}
	
	public String getMarque()
	{
		return marque;
	}
	
	public String getProprietaire()
	{
		return proprietaire;
	}

}
