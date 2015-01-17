package parking.business;

public class Moto extends Vehicule
{

	public Moto(String imm, String mod, String mar, String prop)
	{
		super(imm, mod, mar, prop);
	}

	public boolean isTransporteur()
	{
		return false;
	}
}
