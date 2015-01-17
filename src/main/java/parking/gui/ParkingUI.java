package parking.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import parking.business.Constante;
import parking.business.Parking;
import parking.exception.PasAssezDObservateurException;
import parking.exception.PlaceOccupeeException;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParkingUI
{
	private JFrame frame;
	private Parking parking = Parking.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ParkingUI window = new ParkingUI();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private List<PlaceButton> placeButtons = new ArrayList<PlaceButton>();

	/**
	 * Create the application.
	 * 
	 * @throws PasAssezDObservateurException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public ParkingUI() throws PasAssezDObservateurException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws PasAssezDObservateurException
	 */
	private void initialize() throws PasAssezDObservateurException
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmNewMenuItem = new JMenuItem("Afficher");
		mnFichier.add(mntmNewMenuItem);
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Imprimer");
		mnFichier.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Documentation");
		mnFichier.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("A propos");
		mnFichier.add(mntmNewMenuItem_3);

		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);

		JMenuItem mntmAjouterUnVhicule = new JMenuItem("Ajouter un véhicule");
		mntmAjouterUnVhicule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AddVehicule av = new AddVehicule();
				av.setModal(true);
				av.setVisible(true);
				if(!av.getValue()) return;
				try
				{
					Parking.getInstance().park(av.getVehicule());
				}
				catch (PlaceOccupeeException e)
				{
					JOptionPane.showMessageDialog(null, "Plus de place disponible", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnActions.add(mntmAjouterUnVhicule);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (int i = 0; i < Constante.nbPlaceParticulier + Constante.nbPlaceTranporteur; ++i)
			placeButtons.add(new PlaceButton());
		
		parking.observePlaces(placeButtons);
		for (PlaceButton pb : placeButtons)
		{
			JPanel jp = new JPanel();
			if(pb.getPlace().isTransporteur())
				pb.setPreferredSize(new Dimension(32, 90));
			else
				pb.setPreferredSize(new Dimension(32, 51));
			jp.setPreferredSize(new Dimension(32, 90));
			
			jp.add(pb);
			panel.add(jp);
		}
		frame.setLocationRelativeTo(null);
	}
}
