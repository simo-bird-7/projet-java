package parking.business;

import parking.exception.PlaceOccupeeException;

public class PlaceTransporteur extends Place
{

	@Override
	public void park(Vehicule v) throws PlaceOccupeeException
	{
		if(reserve && v.immatriculation != reservedImmat) throw new PlaceOccupeeException();
		vehicule = v;
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean isTransporteur()
	{
		return true;
	}
}
