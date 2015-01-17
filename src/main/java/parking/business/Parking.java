package parking.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Stack;

import parking.exception.*;
import parking.gui.PlaceButton;

public class Parking
{
	private static Parking instance;
	private List<Place> places;
	// A modifier pour changer la langue (Doit fournir le fichier *Langue*.txt
	private String langue = "Francais";

	public static Parking getInstance()
	{
		if (instance == null) instance = new Parking();
		return instance;
	}

	protected Parking()
	{
		places = new ArrayList<Place>();
		for(int i = 0; i < Constante.nbPlaceParticulier; ++i)
			places.add(new PlaceParticulier());
		for(int i = 0; i < Constante.nbPlaceTranporteur; ++i)
			places.add(new PlaceTransporteur());
	}

	public boolean vehiculeExiste(Vehicule v)
	{
		return places.contains(v);
	}

	public void park(Vehicule v) throws PlaceOccupeeException
	{
		for (Place place : places)
		{
			try
			{
				place.park(v);
				return;
			}
			catch (PlaceOccupeeException poe)
			{
				if(place.isTransporteur())
					System.out.println("TR");
			}
		}
		System.out.println("BITTETWRTI");
		throw new PlaceOccupeeException();
	}

	public void park(Vehicule v, int place) throws PlaceOccupeeException
	{
		park(v, places.get(place));
	}

	public void park(Vehicule v, Place place) throws PlaceOccupeeException
	{
		if (!place.isReserve(v.getImmatriculation()) && place.isFree()) place.park(v);
		else throw new PlaceOccupeeException();
	}

	public Vehicule unpark(Place p) throws PlaceLibreException, PlaceOccupeeException
	{
		Vehicule v = p.getParkedVehicule();
		if (v == null) throw new PlaceLibreException();
		p.retirer();
		reorganiserPlace();
		return v;
	}

	public Vehicule unpark(int place) throws PlaceLibreException, PlaceOccupeeException
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

	public Place bookPlace(Vehicule v) throws PlusAucunePlaceException
	{
		for (Place place : places)
			if (!place.isReserve() && place.isFree())
			{
				place.reserver(v.immatriculation);
				return place;
			}
		throw new PlusAucunePlaceException();
	}

	public Place bookPlace(Vehicule v, int emplacement) throws PlusAucunePlaceException
	{
		return bookPlace(v, places.get(emplacement));
	}

	public Place bookPlace(Vehicule v, Place p) throws PlusAucunePlaceException
	{
		if (!p.isReserve() && p.isFree())
		{
			p.reserver(v.getImmatriculation());
			return p;
		}
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

	public Vehicule retirerVehicule(String immat) throws PlaceOccupeeException
	{
		try
		{
			for (int i = 0; i < places.size() - 1; ++i)
				if (!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmatriculation() == immat)
					return unpark(i);
		}
		catch (PlaceLibreException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	protected void reorganiserPlace() throws PlaceLibreException, PlaceOccupeeException
	{
		Stack<Place> placesLibres = new Stack<Place>();
		for (Place p : places.subList(0, Constante.nbPlaceParticulier))
			if (!p.isTransporteur() && p.isFree()) placesLibres.push(p);
		for (Place p : places.subList(Constante.nbPlaceParticulier,
				Constante.nbPlaceParticulier + Constante.nbPlaceTranporteur))
			if (!p.isFree() && !p.getParkedVehicule().isTransporteur()) park(
					unpark(places.get(p.getNumero())), placesLibres.pop());
	}
	
	public void observePlaces(List<? extends Observer> observer) throws PasAssezDObservateurException
	{
		if(observer.size() != places.size()) throw new PasAssezDObservateurException();
		Iterator<Place> placeIt = places.iterator();
		Iterator<? extends Observer> obsIt = observer.iterator();
		while(placeIt.hasNext() && obsIt.hasNext())
		{
			Place p = placeIt.next();
			PlaceButton pb = (PlaceButton) obsIt.next();
			pb.setPlace(p);
			p.addObserver(pb);
		}
	}
}
