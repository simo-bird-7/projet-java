package parking.business;

public class BrandFee implements FeeStrategy
{
	private static final long serialVersionUID = -4928362169669642830L;

	@Override
	public double calculerCout(Vehicule v)
	{
		if(v.getMarque() == "BMW")
			return 0; // Supreme Gentleman
		return 10;
	}

	@Override
	public String description()
	{
		return "selon la marque";
	}
}
