package parking.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Facture implements Serializable
{
	private static final long serialVersionUID = -9039700930999803534L;
	private static int numFactures = 0;
	private static List<Facture> factures = load();
	private FeeStrategy tarif = Constante.tarif;
	private int numFacture = ++numFactures;

	Vehicule vehicule;

	public static List<Facture> getFactures()
	{
		return factures;
	}
	
	public Facture(Vehicule v)
	{
		vehicule = v;
		factures.add(this);
	}
	
	public double getPrice()
	{
		return tarif.calculerCout(vehicule);
	}
	
	public String toString()
	{
		return "Facture n" + numFacture + ":\n" + vehicule + "\nTarif calculé " + tarif.description() + "\n--------------------------------------------------\n" + getPrice();
	}

	@SuppressWarnings("unchecked")
	public static List<Facture> load()
	{
		List<Facture> factures = new ArrayList<Facture>();
		File fact = new File("factures/factures.bin");
		if(!fact.exists()) return factures;
		FileInputStream fos;
		ObjectInputStream oos;
		try
		{
			fos = new FileInputStream("factures/" + "factures.bin");
			oos = new ObjectInputStream(fos);
			factures = (List<Facture>) oos.readObject();
			numFactures = factures.size();
			oos.close();
			fos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factures;
	}
	
	public static void save() throws IOException
	{
		File dir = new File("factures");
		if(!dir.exists())
			dir.mkdir();
		FileOutputStream fos = new FileOutputStream("factures/" + "factures.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(factures);
		oos.close();
	}
}
