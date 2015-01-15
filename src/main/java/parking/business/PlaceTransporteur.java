package parking.business;

public class PlaceTransporteur extends Place
{

	@Override
	public void park(Vehicule v)
	{
		vehicule = v;
	}

	@Override
	public boolean isTransporteur()
	{
		return true;
	}
}
