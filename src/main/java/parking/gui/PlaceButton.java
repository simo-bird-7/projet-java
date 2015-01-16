package parking.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import parking.business.Parking;
import parking.business.Place;
import parking.exception.PlaceDisponibleException;
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlusAucunePlaceException;

public class PlaceButton extends JButton implements java.util.Observer
{
	private static final long serialVersionUID = 1L;
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem garerVehicule   = new JMenuItem(),
					  reserverPlace   = new JMenuItem(),
					  libererPlace    = new JMenuItem(),
					  retirerVehicule = new JMenuItem(),
					  showInfo        = new JMenuItem();

	private static int nbInstance = 0;
	private Place place;
	private int numero;
	private Color c = Color.green;

	protected void paintComponent(Graphics g)
	{
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Place getPlace()
	{
		return place;
	}
	
	public void setPlace(Place p)
	{
		place = p;
		if(!p.isFree())
			c = Color.red;
		else if(p.isReserve())
			c = Color.orange;
		else
			c = Color.green;
		repaint();
	}
	
	public PlaceButton()
	{
		numero = nbInstance++;
		garerVehicule.setText("Garer une nouvelle voiture ici");
		garerVehicule.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AddVehicule av = new AddVehicule();
				av.setModal(true);
				av.setVisible(true);
				if(!av.getValue()) return;
				try
				{
					av.getVehicule().isTransporteur();
					Parking.getInstance().park(av.getVehicule(), numero);
				}
				catch (PlaceOccupeeException e)
				{
					JOptionPane.showMessageDialog(null, "Impossible de garer ce véhicule ici!", "Opération impossible", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		reserverPlace.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				AddVehicule av = new AddVehicule();
				av.setModal(true);
				av.setVisible(true);
				if(!av.getValue()) return;
				try
				{
					Parking.getInstance().bookPlace(av.getVehicule(), numero);
				}
				catch (PlusAucunePlaceException e)
				{
					JOptionPane.showMessageDialog(null, "Impossible de réserver cette place!", "Opération impossible", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		libererPlace.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Parking.getInstance().freePlace(numero);
				}
				catch (PlaceDisponibleException e)
				{
					JOptionPane.showMessageDialog(null, "Impossible de liberer cette place!", "Opération impossible", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		retirerVehicule.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Parking.getInstance().unpark(numero);
				}
				catch (PlaceLibreException e)
				{
					JOptionPane.showMessageDialog(null, "Cette place est déjà libre !", "Opération impossible", JOptionPane.ERROR_MESSAGE);
				}
				catch (PlaceOccupeeException e)
				{
					e.printStackTrace();
				}
			}
		});

		reserverPlace.setText("Réserver cette place");
		libererPlace.setText("Liberer cette place");
		retirerVehicule.setText("Retirer le véhicule garé");
		popup.add(libererPlace);
		popup.add(reserverPlace);
		popup.add(garerVehicule);
		popup.add(retirerVehicule);
		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				popup.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		Place sender = (Place) o;
		if(!sender.isFree())
			c = Color.red;
		else if(sender.isReserve())
			c = Color.orange;
		else
			c = Color.green;
		repaint();
	}
}
