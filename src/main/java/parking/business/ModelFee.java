package parking.business;

public class ModelFee implements FeeStrategy
{
	public double calculerCout(Vehicule v)
	{
		if(v.getMarque() == "BMW")
			return 0; // Supreme Gentleman
		return 10;
	}
}
