package parking.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Parking
{
	private static Parking instance;
	private ArrayList<Place> places;
	// A modifier pour changer la langue (Doit fournir le fichier *Langue*.txt
	private String langue = "Francais";
	
	public Parking getInstance()
	{
		if (instance == null)
			instance = new Parking();
		return instance;
	}
	
	public boolean vehiculeExiste(Vehicule v)
	{
		return  places.contains(v);
	}
	
	public void park (Vehicule v) throws PlaceOccuppeeException
	{
		for(Place place : places)
		{
			if(place.isFree())
				place.park(v);
				return;
		}
		throw new PlaceOccuppeeException();
	}

	public void park(Vehicule v, int place) throws PlaceOccuppeeException
	{
		if(places.get(place).isFree())
			places.get(place).park(v);
		else
			throw new PlaceOccuppeeException();
			
	}

	public Vehicule unpark(int place) throws PlaceLibreException
	{
		Vehicule v = places.get(place).getParkedVehicule();
		if(v == null)
			throw new PlaceLibreException();
		places.get(place).libere();
		return v;
	}
	
	public void etatParking ()
	{
		try {
			LecteurFichierLangue lg = new LecteurFichierLangue(langue);
			System.out.print(lg.getEtat() + " : \n [");
			for (int i = 0 ; i < places.size() -1; ++i)
			{
				System.out.print(lg.getPlaceNum() + " : " + i + " : ");
				if (places.get(i).isFree())
					System.out.print(lg.getLibre());
				else
					System.out.print(lg.getOccuppee());
				if (places.get(i).isReserve())
					System.out.println(lg.getReservee());
			}
			System.out.print("];");
		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Language File not Found");
		}
		catch (IOException e)
		{
			System.out.println("Language File w/ wrong structure");
		}
	}

	public Place bookPlace()
	{
		for(Place place : places)
			if(!place.isReserve() && place.isFree())
			{
				place.reserver();
				return place;
			}
		throw new PlusAucunePlaceException();
	}

	public Place bookPlace(int emplacement)
	{
		if(!places.get(emplacement).isReserve() && places.get(emplacement).isFree())
			return places.get(emplacement);
		throw new PlusAucunePlaceException();
	}

	public void freePlace(int i)
	{
		if (!places.get(i).isReserve())
			throw new PlaceDisponibleException();
		else
			places.get(i).liberer();	
	}
	
	public int getLocation(String immat)
	{
		for (int i = 0 ; i < places.size() -1; ++i)
			if(!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmat() == immat)
				return i;
		return -1;
	}
	
	public Vehicule retirerVehicule(String immat)
	{
		try 
		{
			for (int i = 0 ; i < places.size() -1; ++i)
				if(!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmat() == immat)
					return unpark(i);
		}
		catch (PlaceLibreException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void reorganiserPlace()
	{
		for(int i = 0;i < places.size(); ++i)
		{
			if (places.get(i).isTransporteur() && !places.get(i).getParkedVehicule().isTransporteur())
				for(int j = 0; j < places.size(); ++j)
					if(!places.get(i).isTransporteur && places.get(j).isFree())
						park(unpark(i), j);
		}
	}
}
