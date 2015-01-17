package parking.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import parking.business.Facture;
import parking.business.Vehicule;
import parking.business.Voiture;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FactureDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Vehicule vehicule;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			FactureDialog dialog = new FactureDialog(new Voiture("sdf", "s", "sd", "sdf"));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Vehicule getVehicule()
	{
		return vehicule;
	}

	/**
	 * Create the dialog.
	 */
	public FactureDialog(parking.business.Vehicule arg)
	{
		Facture f = new Facture(arg);
		setBounds(100, 100, 287, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		final JLabel lblcont;
		contentPanel.setLayout(null);
		{
			lblcont = new JLabel("[cont]");
			lblcont.setBounds(12, 12, 255, 118);
			lblcont.setMaximumSize(new Dimension(280, 200));
//			lblcont.setText("<html></html>");
			lblcont.setText("<html>" + f.toString().replace("\n", "<br>") + "</html>");
			contentPanel.add(lblcont);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setModal(true);
		setVisible(true);
	}

	public boolean getValue()
	{
		return vehicule != null;
	}

}
