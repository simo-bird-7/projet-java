package parking.business;

import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;

public abstract class Place extends java.util.Observable
{
	static int nbInstance;

	int numero;
	boolean reserve = false;

	protected Vehicule vehicule = null; // le vehicule garé à cette place

	public Place()
	{
		numero = nbInstance;
		nbInstance++;
	}

	public boolean isFree()
	{
		return !isReserve() && vehicule == null;
	}

	public boolean isReserve()
	{
		return reserve;
	}

	public Vehicule getParkedVehicule()
	{
		return vehicule;
	}

	public void liberer()
	{
		vehicule = null;
		notifyObservers();
	}

	public void reserver() throws PlusAucunePlaceException
	{
		if (reserve) throw (new PlusAucunePlaceException());
		else reserve = true;
		notifyObservers();
	}

	public abstract boolean isTransporteur();

	public abstract void park(Vehicule v) throws PlaceOccupeeException;
}
