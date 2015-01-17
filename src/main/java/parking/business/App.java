package parking.business;

import java.io.IOException;

import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;

public class App
{
	public static void main(String[] args) throws PlaceOccupeeException, PlaceLibreException, IOException
	{
		Parking parking = Parking.getInstance();
		parking.park(new Voiture("123", "sdf", "BMW", "DIPONT"), 1);
		Facture f = new Facture(parking.unpark(parking.getLocation("123")));
		System.out.println(f);
		Facture.save();
	}
}
