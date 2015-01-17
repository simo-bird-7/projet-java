package parking.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import parking.business.Facture;
import parking.business.Vehicule;

import javax.swing.AbstractListModel;

public class FacturesHist extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args)
	{
		try
		{
			FacturesHist dialog = new FacturesHist();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FacturesHist()
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));

		{
			JList list = new JList();
			list.setModel(new AbstractListModel()
			{
				public int getSize()
				{
					return Facture.getFactures().size();
				}

				public Object getElementAt(int index)
				{
					return Facture.getFactures().get(index);
				}
			});

			list.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent evt)
				{
					JList list = (JList) evt.getSource();
					if (evt.getClickCount() == 2)
					{
						int index = list.locationToIndex(evt.getPoint());
						FactureDialog fd = new FactureDialog((Facture) list
								.getSelectedValue());
						fd.setModal(true);
						fd.setVisible(true);

					}
				}
			});
			contentPanel.add(list);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
