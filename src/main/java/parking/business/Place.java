package parking.business;

public abstract class Place {

	public boolean isFree()
	{
		return true;
		// TODO Auto-generated method stub
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