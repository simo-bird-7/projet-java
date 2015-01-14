package parking.business;

import parking.exception.PlusAucunePlaceException;

public abstract class Place {
	
	int numero;
	static int nbInstance;
	Vehicule vehicule = null; // le vehicule garé à cette place
	boolean reserve = false; 

	public Place(){
		numero = nbInstance;
		nbInstance++;
	}
	
	public boolean isFree()
	{
		return (vehicule == null);
	}
	
	public abstract void park(Vehicule v);
	
	public void libere() {
		vehicule = null;
	}
	
	public Vehicule getParkedVehicule() {
		return vehicule;
	}
	
	public boolean reserve(){
		return reserve;
	}
	
	public void reserver() throws PlusAucunePlaceException {
		if (reserve == true){
			throw(new PlusAucunePlaceException());
		}
		else{
			reserve = true;
		}
	}
}