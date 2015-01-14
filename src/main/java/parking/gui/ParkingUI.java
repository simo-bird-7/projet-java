package parking.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ParkingUI
{
	private JFrame frame;
	private JTextField immatriculation;
	private JTextField modele;
	private JTextField marque;
	private JTextField proprietaire;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingUI window = new ParkingUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParkingUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(0, 0, 85, 453);
		desktopPane.add(toolBar);
		
		JButton btnNewButton_1 = new JButton("Parquer");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Réserver");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Libérer");
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Chercher");
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Retirer");
		toolBar.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Immatriculation");
		panel.add(lblNewLabel);
		
		immatriculation = new JTextField();
		panel.add(immatriculation);
		immatriculation.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Modele");
		panel.add(lblNewLabel_1);
		
		modele = new JTextField();
		panel.add(modele);
		modele.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Marque");
		panel.add(lblNewLabel_2);
		
		marque = new JTextField();
		panel.add(marque);
		marque.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Propriétaire");
		panel.add(lblNewLabel_3);
		
		proprietaire = new JTextField();
		panel.add(proprietaire);
		proprietaire.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter véhicule");
		panel_1.add(btnNewButton, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Afficher");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Imprimer");
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Documentation");
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("A propos");
		menuBar.add(mntmNewMenuItem_3);
	}
}
