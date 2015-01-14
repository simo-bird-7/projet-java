package parking.business;

public abstract class Place {
	
	Vehicule vehicule = null; // le vehicule garé à cette place

	public boolean isFree()
	{
		return (vehicule == null);
	}
	
	public abstract void park(Vehicule v);
	
	public void libere() {
		// TODO Auto-generated method stub
		
	}
	public Vehicule getParkedVehicule() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean reserve() {
		// TODO Auto-generated method stub
		return false;
	}
}