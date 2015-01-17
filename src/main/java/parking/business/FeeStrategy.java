package parking.business;

import java.io.Serializable;

public interface FeeStrategy extends Serializable
{
	public double calculerCout(Vehicule v);
	public String description();
}
