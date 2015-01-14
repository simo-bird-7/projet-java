package parking.business;

import parking.exception.PlaceOccupeeException;

public class PlaceParticulier extends Place{
	
	@Override
	public void park(Vehicule v) throws PlaceOccupeeException {
		if(vehicule.isTransporteur())
			throw new PlaceOccupeeException();
		else
			vehicule = v;
	}
	
	@Override
	public boolean isTransporteur()
	{
		return true;
	}

}
