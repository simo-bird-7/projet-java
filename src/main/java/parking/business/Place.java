package parking.business;

import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;

public abstract class Place extends java.util.Observable
{
	static int nbInstance = 0;

	int numero;
	String reservedImmat;
	boolean reserve = false;

	protected Vehicule vehicule = null; // le vehicule garé à cette place

	public Place()
	{
		numero = nbInstance++;
	}

	public boolean isFree()
	{
		return vehicule == null;
	}

	public boolean isReserve()
	{
		return reserve;
	}

	public boolean isReserve(String immat)
	{
		return !immat.equals(reservedImmat) && reserve;
	}

	public Vehicule getParkedVehicule()
	{
		return vehicule;
	}

	public void liberer()
	{
		reserve = false;
		reservedImmat = null;
		setChanged();
		notifyObservers();
	}

	public void reserver(String immat) throws PlusAucunePlaceException
	{
		if (reserve) throw (new PlusAucunePlaceException());
		else
		{
			reservedImmat = immat;
			reserve = true;
		}
		setChanged();
		notifyObservers();
	}
	
	public int getNumero()
	{
		return numero;
	}

	public abstract boolean isTransporteur();

	public abstract void park(Vehicule v) throws PlaceOccupeeException;

	public void retirer()
	{
		Vehicule tmp = vehicule;
		vehicule = null;
		setChanged();
		System.out.println("Notification");
		notifyObservers(tmp);
	}
}
