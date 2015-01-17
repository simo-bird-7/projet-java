package parking.business;

import parking.exception.PlaceOccupeeException;

public class PlaceParticulier extends Place
{
	@Override
	public void park(Vehicule v) throws PlaceOccupeeException
	{
		if (v.isTransporteur()) throw new PlaceOccupeeException();
		else vehicule = v;
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean isTransporteur()
	{
		return false;
	}

}
