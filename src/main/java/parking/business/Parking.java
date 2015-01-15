package parking.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import parking.exception.*;

public class Parking
{
	private static Parking instance;
	private List<Place> places;
	// A modifier pour changer la langue (Doit fournir le fichier *Langue*.txt
	private String langue = "Francais";

	public Parking getInstance()
	{
		if (instance == null) instance = new Parking();
		return instance;
	}

	protected Parking()
	{
		places = Collections.nCopies(Constante.nbPlaceParticulier,
				new PlaceParticulier());
		places.addAll(Collections.nCopies(Constante.nbPlaceTranporteur,
				new PlaceTransporteur()));
	}

	public boolean vehiculeExiste(Vehicule v)
	{
		return places.contains(v);
	}

	public void park(Vehicule v) throws PlaceOccupeeException
	{
		for (Place place : places)
			try
			{
				place.park(v);
				return;
			}
			catch (PlaceOccupeeException poe)
			{// On essaye de garer la voiture partout.
			}
		throw new PlaceOccupeeException();
	}

	public void park(Vehicule v, int place) throws PlaceOccupeeException
	{
		park(v, places.get(place));
	}

	public void park(Vehicule v, Place place) throws PlaceOccupeeException
	{
		if (!place.isReserve() && place.isFree()) place.park(v);
		else throw new PlaceOccupeeException();
	}

	public Vehicule unpark(Place p) throws PlaceLibreException
	{
		Vehicule v = p.getParkedVehicule();
		if (v == null) throw new PlaceLibreException();
		p.liberer();
		return v;
	}

	public Vehicule unpark(int place) throws PlaceLibreException
	{
		return unpark(places.get(place));
	}

	public void etatParking()
	{
		try
		{
			LecteurFichierLangue lg = new LecteurFichierLangue(langue);
			System.out.print(lg.getEtat() + " : \n [");
			for (int i = 0; i < places.size() - 1; ++i)
			{
				System.out.print(lg.getPlaceNum() + " : " + i + " : ");
				if (places.get(i).isFree()) System.out.print(lg.getLibre());
				else System.out.print(lg.getOccuppee());
				if (places.get(i).isReserve()) System.out.println(lg
						.getReservee());
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

	public Place bookPlace() throws PlusAucunePlaceException
	{
		for (Place place : places)
			if (!place.isReserve() && place.isFree())
			{
				place.reserver();
				return place;
			}
		throw new PlusAucunePlaceException();
	}

	public Place bookPlace(int emplacement) throws PlusAucunePlaceException
	{
		return bookPlace(places.get(emplacement));
	}

	public Place bookPlace(Place p) throws PlusAucunePlaceException
	{
		if (!p.isReserve() && p.isFree()) return p;
		throw new PlusAucunePlaceException();
	}

	public void freePlace(int i) throws PlaceDisponibleException
	{
		freePlace(places.get(i));
	}

	public void freePlace(Place p) throws PlaceDisponibleException
	{
		if (!p.isReserve()) throw new PlaceDisponibleException();
		else p.liberer();
	}

	public int getLocation(String immat)
	{
		for (int i = 0; i < places.size() - 1; ++i)
			if (!(places.get(i).isFree())
					&& places.get(i).getParkedVehicule().getImmatriculation() == immat) return i;
		return -1;
	}

	public Vehicule retirerVehicule(String immat)
	{
		try
		{
			for (int i = 0; i < places.size() - 1; ++i)
				if (!(places.get(i).isFree())
						&& places.get(i).getParkedVehicule()
								.getImmatriculation() == immat) return unpark(i);
		}
		catch (PlaceLibreException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void reorganiserPlace() throws PlaceLibreException,
			PlaceOccupeeException
	{
		Stack<Place> placesLibres = new Stack<Place>();
		for (Place p : places.subList(0, Constante.nbPlaceParticulier))
			if (!p.isTransporteur() && p.isFree()) placesLibres.push(p);
		for (Place p : places.subList(Constante.nbPlaceParticulier,
				Constante.nbPlaceParticulier + Constante.nbPlaceTranporteur))
			if (!p.isFree() && !p.getParkedVehicule().isTransporteur()) park(
					unpark(p), placesLibres.pop());
	}
}
