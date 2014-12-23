package com.iut.java;

import java.util.ArrayList;

public class Parking {

	private static Parking instance;
	private ArrayList<Place> places;
	
	public Parking getInstance(){
		if (instance == null)
			instance = new Parking();
		return instance;
	}
	
	public boolean vehiculeExiste(Vehicule v)
	{
		return  places.contains(v);
	}
	
	public void park (Vehicule v)
	{
		for(Place place : places)
		{
			if(place.isFree())
				place.park(v);
		}
	}
	public void park(Vehicule v, int place)
	{
		if(places.get(place).isFree())
		{
			places.get(place).park(v);
		}
	}
	public Vehicule unpark(int place)
	{
		Vehicule v = places.get(place).getParkedVehicule();
		if(v == null)
			//throw PlaceLibreException;
			;
		places.get(place).libere();
		return v;
	}
	
	public void etatParking ()
	{
		System.out.print("Etat du parking : \n [");
		for (int i = 0 ; i < places.size() -1; ++i) {
			System.out.print("Place numéro : " + i + " : ");
			if (places.get(i).isFree())
				System.out.print("Libre");
			else
				System.out.print("Occupée");
			if (places.get(i).reserve())
				System.out.println(" et Réservée");
		}
		System.out.print("];");
	}
	public Place bookPlace()
	{
		for(Place place : places)
		{
			if(place.isFree())
				return place;
		}
		return null;
	}
	public Place bookPlace(int emplacement)
	{
		if(places.get(emplacement).isFree())
			return places.get(emplacement);
		return null;
	}
	public void freePlace()
	{
		//Ambigüe
	}
	public int getLocation(String immat)
	{
		for (int i = 0 ; i < places.size() -1; ++i) {
			if(!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmat() == immat)
				return i;
		}
		return -1;
	}
	public Vehicule retirerVehicule(String immat)
	{
		for (int i = 0 ; i < places.size() -1; ++i) {
			if(!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmat() == immat)
				return unpark(i);
		}
		return null;
	}
	public void reorganiserPlace()
	{
		// TODO 
	}
	
}
